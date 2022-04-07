package pack;

import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3IRSensor;
import lejos.robotics.SampleProvider;

public class BoxDetectThread extends Thread {
	private float distanceLeft = 0;
	private float distanceRight = 0;
	private EV3IRSensor distanceSensorLeft;
	private EV3IRSensor distanceSensorRight;
	private boolean flag;
	
	BoxDetectThread() {
		flag = false;
		distanceSensorLeft = new EV3IRSensor(SensorPort.S2);
		distanceSensorRight = new EV3IRSensor(SensorPort.S4);
	}

	public void Stop(){
		flag = true;
	}
	public void run() {
		SampleProvider distanceModeLeft = distanceSensorLeft.getDistanceMode();
		float valueLeft[] = new float[distanceModeLeft.sampleSize()];
		
		SampleProvider distanceModeRight = distanceSensorRight.getDistanceMode();
		float valueRight[] = new float[distanceModeRight.sampleSize()];
		
		while (!flag) {
			distanceModeLeft.fetchSample(valueLeft, 0);
			distanceLeft = valueLeft[0]; // centimeter
			
			distanceModeRight.fetchSample(valueRight, 0);
			distanceRight = valueRight[0]; // centimeter
		}
	}

	public float getDistance() {
		return Math.min(distanceLeft, distanceRight);
	}
}