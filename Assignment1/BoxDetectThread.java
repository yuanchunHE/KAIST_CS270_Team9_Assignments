package pack;

import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3IRSensor;
import lejos.robotics.SampleProvider;

public class BoxDetectThread extends Thread {
	private float distance = 0;
	private EV3IRSensor distanceSensor;
	private boolean flag;
	
	BoxDetectThread() {
		flag = false;
		distanceSensor = new EV3IRSensor(SensorPort.S2);
	}

	public void Stop(){
		flag = true;
	}
	public void run() {
		SampleProvider distanceMode = distanceSensor.getDistanceMode();
		float value[] = new float[distanceMode.sampleSize()];
		
		while (!flag) {
			distanceMode.fetchSample(value, 0);
			distance = value[0]; // centimeter
		}
	}

	public float getDistance() {
		return distance;
	}
}