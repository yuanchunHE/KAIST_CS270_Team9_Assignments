package pack;

import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.robotics.SampleProvider;
//import lejos.hardware.Keys;

public class TouchDetectThread extends Thread {
	private EV3TouchSensor touchSensor;
	private boolean flag;
	private int touchValue;

	TouchDetectThread() {
		flag = false;
		touchSensor = new EV3TouchSensor(SensorPort.S1);
		touchValue = 0;
	}

	public void Stop() {
		flag = true;
	}

	public void run() {
		final SampleProvider sp = touchSensor.getTouchMode();
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

	public boolean isTouched() {
		return touchValue == 1;
	}
}
