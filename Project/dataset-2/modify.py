#from PIL import Image
import cv2
import numpy as np

filenames = ['img'+str(i).zfill(2)+'.png' for i in range(148)]
newFilenames = ['img'+str(i).zfill(3)+'.png' for i in range(148)]


def cutting(image, tl, tr, dr, dl):
    xSize, ySize = 200, 200
    cimage = [[[0, 0, 0] for i in range(xSize)] for j in range(ySize)]
    for i in range(xSize):
        for j in range(ySize):
            xRate = (i / (xSize - 1), 1.0 - i / (xSize - 1))
            yRate = (j / (ySize - 1), 1.0 - j / (ySize - 1))
            for k in range(3):
                x = int(tl[0]*xRate[0]*yRate[0] + tr[0]*xRate[1]*yRate[0] + dr[0]*xRate[1]*yRate[1] + dl[0]*xRate[0]*yRate[1])
                y = int(tl[1]*xRate[0]*yRate[0] + tr[1]*xRate[1]*yRate[0] + dr[1]*xRate[1]*yRate[1] + dl[1]*xRate[0]*yRate[1])
                cimage[j][i][k] = image[x][y][k]
    return np.array(cimage)

for (filename, newFilename) in zip(filenames, newFilenames):
    # read image
    #image = Image.open('../dataset-1/' + filename)
    image = cv2.imread('../dataset-1/' + filename)
    #print(image.shape) == (480, 720, 3)

    #draw line
    #lineColor = (0,0,255)
    #linePoint = [(295,210), (460,210), (480, 480), (295,480)]
    #cv2.line(image, linePoint[0], linePoint[1], lineColor, thickness=2, lineType=cv2.LINE_AA)
    #cv2.line(image, linePoint[1], linePoint[2], lineColor, thickness=2, lineType=cv2.LINE_AA)
    #cv2.line(image, linePoint[2], linePoint[3], lineColor, thickness=2, lineType=cv2.LINE_AA)
    #cv2.line(image, linePoint[3], linePoint[0], lineColor, thickness=2, lineType=cv2.LINE_AA)

    # save image
    cv2.imwrite(newFilename, cutting(image,(210,295),(210,460),(479,480),(479,295)))