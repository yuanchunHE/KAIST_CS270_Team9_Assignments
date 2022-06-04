package test_motor;

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


public class FruitDetectThread extends Thread {
    SocketWrap socket;
    private String status;

    FruitDetectThread() {
    	try {
    		socket = new SocketWrap();
    	} catch (Exception e) {
    		System.out.println("Socket error!" + e);
    	}
        socket.start();

        status = "";
    }

	public void Stop() {
		try {
			socket.Stop();
		} catch (Exception e) {
			System.out.println("Socket stop error!" + e);
		}
    }

	public void run() {
    }

    public int getStatus() {
    	status = socket.getRecvM();
    	System.out.printf("Got %s!\n", status);
    	int result;
    	try {
    		result = Integer.parseInt(status);
    	} catch (Exception e) {
    		System.out.println("Socket getRecvM error!" + e);
    		System.out.println("status:" + status);
    		return -1;
    	}
        return result;
    }

    public boolean areThereFiveFruit() {
    	int temp = getStatus();
        if (status == "") return false;

        return Character.compare(status.charAt(0), '5') == 0 ||
            Character.compare(status.charAt(1), '5') == 0 ||
            Character.compare(status.charAt(2), '5') == 0 ||
            Character.compare(status.charAt(3), '5') == 0;
    }
}
