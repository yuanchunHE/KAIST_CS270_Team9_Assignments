from http import client
import socket

server_address = "10.0.1.12"
port = 8042
size = 1024

sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
sock.bind((server_address, port))
sock.listen(1)

print("waiting ev3Client...")

try:
    client,clientInfo = sock.accept()
    print("connected client",clientInfo)

    while True:
        recvM = client.recv(size)
        if recvM:
            '''
                string expressing the 4 digits NUM
                e.g. string = '1234'
            '''
            result = ''  ###result from cam
            sendData = '\x00\x04' + result
            client.send(sendData.encode('utf-8'))
        else:
            print("disconnected")
            client.close()
            sock.close()
            break
except:
    print("closing socket")
    client.close()
    sock.close()    