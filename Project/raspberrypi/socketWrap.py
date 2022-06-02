import socket

class SocketWrap:
    def __init__(self):
        # config setting
        serverAddress = "10.0.1.12"
        port = 8042

        self.sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        self.sock.bind((serverAddress, port))
        self.sock.listen(1)

        client, clientInfo = self.sock.accept()
        self.client = client
        print("connected client : ", clientInfo)

    def send(self, msg):
        # send message using socket communication
        '''
        msg format = [0-5][0-5][0-5][0-5]
        = <# of strawberry><# of banana><# of lime><# of plum>
        '''
        recvSize = 1024
        #recvM = self.client.recv(recvSize)
        sendData = '\x00\x04' + msg
        client.send(sendData.encode('utf-8'))

    def receive(self):
        # receive message using socket communication
        return 0
    
    def close():
        self.client.close()
        self.sock.close()