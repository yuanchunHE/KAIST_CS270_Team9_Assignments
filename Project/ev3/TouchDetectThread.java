package test_motor;

import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.robotics.SampleProvider;
//import lejos.hardware.Keys;

public class TouchDetectThread extends Thread {
	private EV3TouchSensor touchSensor1;
	private EV3TouchSensor touchSensor2;
	private boolean flag;
	private int touch1Value, touch2Value;

	TouchDetectThread() {
		flag = false;
		touchSensor1 = new EV3TouchSensor(SensorPort.S1);
		touchSensor2 = new EV3TouchSensor(SensorPort.S2);
		touch1Value = 0;
		touch2Value = 0;
	}

	public void Stop() {
		flag = true;
	}

	public void run() {
		final SampleProvider sp1 = touchSensor1.getTouchMode();
		final SampleProvider sp2 = touchSensor2.getTouchMode();

		while (!flag) {
			float[] sample1 = new float[sp1.sampleSize()];
			float[] sample2 = new float[sp2.sampleSize()];
			sp1.fetchSample(sample1, 0);
			sp2.fetchSample(sample2, 0);
			touch1Value = (int) sample1[0];
			touch2Value = (int) sample2[0];
		}
	}

	public boolean isTouched1() {
		return touch1Value == 1;
	}
	public boolean isTouched2() {
		return touch2Value == 1;
	}
}
