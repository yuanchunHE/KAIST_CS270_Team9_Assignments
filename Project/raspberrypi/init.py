from camera import Camera
from fruitDetect import FruitDetect
from socket import Socket

def run():
    camera = Camera()
    detector = FruitDetect()
    socket = Socket()

    while True:
        img = camera.capture()
        result = detector.detect(img)
        socket.send(result)


if __name__ == "__main__":
    run()
