package pack;

import lejos.hardware.BrickFinder;
import lejos.hardware.Keys;
import lejos.hardware.ev3.EV3;
import lejos.hardware.lcd.TextLCD;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.Color;

public class Robot {
	public static void main(String[] args) {
		BoxDetectThread boxDetectThread = new BoxDetectThread();
		boxDetectThread.start();

		while (true) {
			System.out.println(boxDetectThread.getDistance());
		}
	}
}