import os
import argparse
import cv2
import numpy as np
import sys
import importlib.util
from socketWrap import SocketWrap

from picamera import PiCamera
from io import BytesIO
from PIL import Image

# Define and parse input arguments

MODEL_NAME = '/home/pi/tflite1/haligaliModel'
GRAPH_NAME = 'detect.tflite'
LABELMAP_NAME = 'labelmap.txt'
min_conf_threshold = float(0.5)
use_TPU = False

# load camera and socket
camera = PiCamera()
camera.start_preview()
socket = SocketWrap()

# Parse input image name and directory. 
# If both an image AND a folder are specified, throw an error

# If neither an image or a folder are specified, default to using 'test1.jpg' for image name

# Import TensorFlow libraries
# If tflite_runtime is installed, import interpreter from tflite_runtime, else import from regular tensorflow
# If using Coral Edge TPU, import the load_delegate library
pkg = importlib.util.find_spec('tflite_runtime')
if pkg:
    from tflite_runtime.interpreter import Interpreter
    if use_TPU:
        from tflite_runtime.interpreter import load_delegate
else:
    from tensorflow.lite.python.interpreter import Interpreter
    if use_TPU:
        from tensorflow.lite.python.interpreter import load_delegate

# If using Edge TPU, assign filename for Edge TPU model
if use_TPU:
    # If user has specified the name of the .tflite file, use that name, otherwise use default 'edgetpu.tflite'
    if (GRAPH_NAME == 'detect.tflite'):
        GRAPH_NAME = 'edgetpu.tflite'


# Get path to current working directory
CWD_PATH = os.getcwd()

# Define path to images and grab all image filenames

# Path to .tflite file, which contains the model that is used for object detection
PATH_TO_CKPT = os.path.join(CWD_PATH,MODEL_NAME,GRAPH_NAME)

# Path to label map file
PATH_TO_LABELS = os.path.join(CWD_PATH,MODEL_NAME,LABELMAP_NAME)

# Load the label map
with open(PATH_TO_LABELS, 'r') as f:
    labels = [line.strip() for line in f.readlines()]

# Have to do a weird fix for label map if using the COCO "starter model" from
# https://www.tensorflow.org/lite/models/object_detection/overview
# First label is '???', which has to be removed.
if labels[0] == '???':
    del(labels[0])

# Load the Tensorflow Lite model.
# If using Edge TPU, use special load_delegate argument
if use_TPU:
    interpreter = Interpreter(model_path=PATH_TO_CKPT,
                              experimental_delegates=[load_delegate('libedgetpu.so.1.0')])
    print(PATH_TO_CKPT)
else:
    interpreter = Interpreter(model_path=PATH_TO_CKPT)

interpreter.allocate_tensors()

# Get model details
input_details = interpreter.get_input_details()
output_details = interpreter.get_output_details()
height = input_details[0]['shape'][1]
width = input_details[0]['shape'][2]

floating_model = (input_details[0]['dtype'] == np.float32)

input_mean = 127.5
input_std = 127.5

# Check output layer name to determine if this model was created with TF2 or TF1,
# because outputs are ordered differently for TF2 and TF1 models
outname = output_details[0]['name']

if ('StatefulPartitionedCall' in outname): # This is a TF2 model
    boxes_idx, classes_idx, scores_idx = 1, 3, 0
else: # This is a TF1 model
    boxes_idx, classes_idx, scores_idx = 0, 1, 2

# Loop over every image and perform detection


    # Load image and resize to expected shape [1xHxWx3]
while True:
    stream = BytesIO()
    camera.capture(stream, format='png')
    stream.seek(0)
    image = Image.open(stream)
    image = np.array(image)
    image_rgb = cv2.cvtColor(image, cv2.COLOR_BGR2RGB)
    # print(image_rgb)
    
    imH, imW, _ = image.shape 
    image_resized = cv2.resize(image_rgb, (width, height))
    input_data = np.expand_dims(image_resized, axis=0)

        # Normalize pixel values if using a floating model (i.e. if model is non-quantized)
    if floating_model:
        input_data = (np.float32(input_data) - input_mean) / input_std

    # Perform the actual detection by running the model with the image as input
    interpreter.set_tensor(input_details[0]['index'],input_data)
    interpreter.invoke()

    # Retrieve detection results
    classes = interpreter.get_tensor(output_details[classes_idx]['index'])[0] # Class index of detected objects
    scores = interpreter.get_tensor(output_details[scores_idx]['index'])[0] # Confidence of detected objects
    print(classes, scores)

    # Loop over all detections and draw detection box if confidence is above minimum threshold
    s_num = 0
    l_num = 0
    p_num = 0
    b_num = 0
    nOfDetectedCard = 0
    for i in range(len(scores)):
        if ((scores[i] > min_conf_threshold) and (scores[i] <= 1.0)):
            nOfDetectedCard += 1
            # Draw label
            object_name = labels[int(classes[i])] # Look up object name from "labels" array using class index
            num = len(object_name)
            fruit = object_name[:num-1]
            fruit_num = int(object_name[num-1])
            if(fruit == "strawberry"):
                s_num += fruit_num
            elif(fruit == "lime"):
                l_num += fruit_num
            elif(fruit == "plum"):
                p_num += fruit_num
            else:
                b_num += fruit_num
    
    if(s_num == 10): s_num = 0
    if(l_num == 10): l_num = 0
    if(p_num == 10): p_num = 0
    if(b_num == 10): b_num = 0

    if nOfDetectedCard <= 2:
        result = str(nOfDetectedCard) + str(s_num) + str(l_num) + str(p_num) + str(b_num)
        #print(result)
        if socket.receive():
            socket.send(result)
    