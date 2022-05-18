from picamera import PiCamera
from io import BytesIO
from PIL import Image

class Camera:
    def __init__(self):
        self.camera = PiCamera()
        self.camera.start_preview()

    def captureSave(self, filename='capture.jpg'):
        self.camera.capture(filename)

    def capture(self):
        stream = BytesIO()
        self.camera.capture(stream, format='png')
        stream.seek(0)
        return Image.open(stream)
