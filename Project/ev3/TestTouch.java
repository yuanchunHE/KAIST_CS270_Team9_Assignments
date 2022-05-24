package test;

import lejos.hardware.BrickFinder;
import lejos.hardware.Keys;
import lejos.hardware.ev3.EV3;
import lejos.hardware.lcd.TextLCD;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.robotics.SampleProvider;
import lejos.utility.Delay;
import lejos.hardware.BrickFinder;
import lejos.hardware.Keys;
import lejos.hardware.ev3.EV3;
import lejos.hardware.lcd.TextLCD;

public class TestTouch extends Thread {

	private EV3TouchSensor touch;
	private boolean flag;
	private int touchValue;

	TestTouch() {
		touch = new EV3TouchSensor(SensorPort.S1);
		flag = false;
		touchValue = 0;
	}

	public void Stop() {
		flag = true;
	}

	public void run() {

		final SampleProvider sp = touch.getTouchMode();
		while (!flag) {

			float[] sample = new float[sp.sampleSize()];
			sp.fetchSample(sample, 0);
			touchValue = (int) sample[0];

			// System.out.println("Iteration: {}" + i);
			// System.out.println("Touch: {}" + touchValue);

			// keys.waitForAnyPress();
			// lcd.clear();
		}

	}

	public int getTouchValue() {
		return touchValue;
	}
}
