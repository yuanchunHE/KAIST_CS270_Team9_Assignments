package test;

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

public class TestSocket extends Thread {

	private String serverAddress = "10.0.1.12";
	private int serverPort = 8042;
	private boolean flag;
	
	private Socket socket = null;
	private DataOutputStream streamOut = null;
	private DataInputStream streamIn = null;
	
	private String sendM = "";
	private String recvM = "";
	
	TestSocket() throws UnknownHostException, IOException {
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
	
		while(!flag){
			sendM = "test";
			try {
				streamOut.writeUTF(sendM);
				streamOut.flush();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			try {
				recvM = streamIn.readUTF();
				System.out.println(recvM);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public String getRecvM(){
		return recvM;
	}
	
}
