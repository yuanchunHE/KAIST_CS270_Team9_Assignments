from picamera import PiCamera

class Camera:
    def __init__(self):
        self.camera = PiCamera()
        
    def capture(self):
        self.camera.capture('capture.jpg')
