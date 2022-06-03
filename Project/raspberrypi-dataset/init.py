from camera import Camera

def run():
    camera = Camera()
    path = 'dataset/'
    index = 0
    while True:
        input('index = '+str(index).zfill(2)+ ' : ')
        camera.captureSave(path + 'img'+str(0).zfill(2)+'.png')
        index += 1

if __name__ == "__main__":
    run()
