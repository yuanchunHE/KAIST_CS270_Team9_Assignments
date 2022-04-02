package pack;

import lejos.hardware.port.SensorPort;

public class BoxDetect extends Thread {
	private final int maxDistance = 11;// distance sensorµÄÊý¾Ý
	private DataFlow dataFlow;
	private DistanceSensors distanceSensor;

	public BoxDetect(DataFlow df) {
		dataFlow = df;
		distanceSensor = new DistanceSensors(SensorPort.S2);
	}

	public void run() {
		int count = 0;// num of detected boxes
		boolean firFlag=true;
		boolean secFlag=true;
		
		while (true) {
			if (distanceSensor.getDistance() > maxDistance) {
				dataFlow.setState(1);
			} else {
				dataFlow.setState(2);
				if (firFlag&&count == 0) {
					dataFlow.setBoxDetect1(true);
					count++;
					firFlag=false;
				} else if (secFlag&&count == 1) {
					dataFlow.setBoxDetect2(true);
					if (dataFlow.isBoxDetect1() && dataFlow.isBoxDetect2()
							&& dataFlow.isRedDetect1()
							&& dataFlow.isRedDetect2()) {
						dataFlow.setState(3);
					}
					secFlag = false;
				}
			}
		}
	}
}
