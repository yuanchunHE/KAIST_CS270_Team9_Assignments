//package pack;
//
//import lejos.hardware.port.Port;
//import lejos.hardware.port.SensorPort;
//import lejos.hardware.sensor.EV3IRSensor;
//import lejos.robotics.SampleProvider;
//
//public class DistanceSensors {
//	private float distance;
//	private static EV3IRSensor sensor;
//	private static SampleProvider distanceMode;
//
//	public static void main(String[] args){
//		new DistanceSensors();
//	}
//	
//	DistanceSensors() {
//		sensor = new EV3IRSensor(SensorPort.S2);
//		// System.out.println("In Construct Function");
//		do {
//			distanceMode = sensor.getDistanceMode();
//			float value[] = new float[distanceMode.sampleSize()];
//
//			distanceMode.fetchSample(value, 0);
//			distance = value[0];
//			//System.out.println("" + distance);
//		} while (true);
//	}
//
//	public float getDistance() {
//		return distance;
//	}
//
//}