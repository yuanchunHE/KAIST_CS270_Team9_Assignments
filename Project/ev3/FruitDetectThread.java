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
    private int nDetectedCard;
	private boolean flag;

    FruitDetectThread() {
    	try {
    		socket = new SocketWrap();
    	} catch (Exception e) {
    		System.out.println("Socket error!" + e);
    	}
        socket.start();

		flag = true;
        status = "0000";
		nDetectedCard = 0;
    }

	public void Stop() {
		flag = true;
		try {
			socket.Stop();
		} catch (Exception e) {
			System.out.println("Socket stop error!" + e);
		}
    }

	public void run() {
		String _status, recvm;
		while (!flag) {
			try {
				recvm = socket.getRecvM();
				_status = recvm.substring(1);
				nDetectedCard = Integer.parseInt(recvm.substring(0, 1));
				if (nDetectedCard == 2) status = _status;
			} catch (Exception e) {
				System.out.println("Socket getRecvM error!" + e);
			}
		}
    }

    public int getStatus() {
    	System.out.printf("Got %s!\n", status);
    	return Integer.parseInt(status);
    }

    public int getN() {
		return nDetectedCard;
	}

	public void clearStatus() {
		status = "0000";
		nDetectedCard = 0;
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
