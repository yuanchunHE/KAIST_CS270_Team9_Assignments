package pack;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.rmi.NotBoundException;

import lejos.hardware.BrickFinder;
import lejos.hardware.Keys;
import lejos.hardware.ev3.EV3;
import lejos.hardware.lcd.TextLCD;

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
public class FruitDetectThread extends Thread {
    SocketWrap socket;
    private String status;

    FruitDetectThread() {
        socket = new SocketWrap();
        socket.start();

        status = "";
    }

	public void Stop() {
        socket.Stop();
    }

	public void run() {
    }

    public int getStatus() {
        return status;
    }

    public boolean areThereFiveFruit() {
        if (status == "") return false;
        
        return Character.compare(status.charAt(0), '5') == 0 ||
            Character.compare(status.charAt(1), '5') == 0 ||
            Character.compare(status.charAt(2), '5') == 0 ||
            Character.compare(status.charAt(3), '5') == 0;
    }
}
