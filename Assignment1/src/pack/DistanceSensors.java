package pack;

import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3IRSensor;
import lejos.robotics.SampleProvider;
import lejos.utility.Delay;

public class DistanceSensors {
	private float distance;

	DistanceSensors(Port port) {

		

		// System.out.println("In Construct Function");
		do {
			EV3IRSensor sensor = new EV3IRSensor(port);
			SampleProvider distanceMode = sensor.getDistanceMode();

			float value[] = new float[distanceMode.sampleSize()];
			
			distanceMode.fetchSample(value, 0);
			distance = value[0];
			System.out.println("" + distance);
			Delay.msDelay(100);
			sensor.close();
		} while (true);
	}

	public float getDistance() {
		return distance;
	}

}
