from camera import Camera
from fruitDetect import FruitDetect
from socketWrap import SocketWrap

def run():
    camera = Camera()
    detector = FruitDetect()
    socket = SocketWrap()

    while True:
        img = camera.capture()
        result = detector.detect(img)
        if socket.receive():
            socket.send(result)
        


if __name__ == "__main__":
    run()
