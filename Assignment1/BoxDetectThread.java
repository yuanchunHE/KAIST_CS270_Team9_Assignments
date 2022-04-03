package pack;

import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3IRSensor;
import lejos.robotics.SampleProvider;

public class BoxDetectThread extends Thread {
	private final float maxDistance = 11.0f;
	private float distance = 0;
	private EV3IRSensor distanceSensor;
	
	BoxDetectThread() {
		distanceSensor = new EV3IRSensor(SensorPort.S2);
	}

	public void run() {
		SampleProvider distanceMode = distanceSensor.getDistanceMode();
		float value[] = new float[distanceMode.sampleSize()];
		
		int count = 0;// num of detected boxes
		
		while (true) {
			distanceMode.fetchSample(value, 0);
			distance = value[0]; // centimeter
		}
	}

	public float getDistance() {
		return distance;
	}
}
