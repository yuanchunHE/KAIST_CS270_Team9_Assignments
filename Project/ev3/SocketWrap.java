package test_motor;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocketWrap extends Thread {
	private String serverAddress = "10.0.1.12";
	private int serverPort = 8042;
	private boolean flag;
	
	private Socket socket = null;
	private DataOutputStream streamOut = null;
	private DataInputStream streamIn = null;
	
	private String recvM = "";

    SocketWrap() throws UnknownHostException, IOException {
        flag = false;
        socket = new Socket(serverAddress, serverPort);
        streamIn = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
        streamOut = new DataOutputStream(socket.getOutputStream());
    }

	public void Stop() throws IOException {
		flag = true;
		socket.close();
		streamOut.close();
		streamIn.close();
	}


	public void run() {
		while (!flag) {
			try {
				recvM = streamIn.readUTF();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public String getRecvM(){
		return recvM;
	}
}
