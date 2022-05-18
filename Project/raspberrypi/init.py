from camera import Camera

def run():
    camera = Camera()
    img = camera.capture()
    img.save('test.png')

if __name__ == "__main__":
    run()
    