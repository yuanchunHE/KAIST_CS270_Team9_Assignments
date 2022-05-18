from camera import Camera
from fruitDetect import FruitDetect

def run():
    camera = Camera()
    detector = FruitDetect()
    # img = camera.capture()
    # img.save('test.png')

if __name__ == "__main__":
    run()
