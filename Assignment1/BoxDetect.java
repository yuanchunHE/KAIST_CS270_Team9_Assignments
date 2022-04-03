

import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3IRSensor;
import lejos.robotics.SampleProvider;

public class BoxDetect extends Thread {
	private final int maxDistance = 11;// distance sensor������
	private DataFlow dataFlow;
	private EV3IRSensor distanceSensor;

	BoxDetect(DataFlow df) {
		dataFlow = df;
		distanceSensor = new EV3IRSensor(SensorPort.S2);
	}

	public void run() {
		SampleProvider distanceMode=distanceSensor.getDistanceMode();
		float value[] = new float[distanceMode.sampleSize()];
		
		MoveAPI moveFun=new MoveAPI();
		
		int count = 0;// num of detected boxes
		boolean firFlag = true;
		boolean secFlag = true;
		
		System.out.println("BoxDetect Started!");
		
		while (true) {
			distanceMode.fetchSample(value, 0);
			float centimeter = value[0];
			if (centimeter > maxDistance) {
				dataFlow.setState(1);
			} else {
				dataFlow.setState(2);
				moveFun.turnLeft();
				// calculate the position of the Box
				Coordinate tempPos = new Coordinate();
				CoordinateSys robotPos = new CoordinateSys(
						dataFlow.getRobotPos().facingDirection,
						dataFlow.getRobotPos().x, dataFlow.getRobotPos().y);
				String str = robotPos.facingDirection;

				switch (str) {
				case "+x":
					tempPos.x = robotPos.x + 1;
					tempPos.y = robotPos.y;
					break;
				case "-x":
					tempPos.x = robotPos.x - 1;
					tempPos.y = robotPos.y;
					break;
				case "+y":
					tempPos.x = robotPos.x;
					tempPos.y = robotPos.y + 1;
					break;
				case "-y":
					tempPos.x = robotPos.x;
					tempPos.y = robotPos.y - 1;
					break;
				}
				
				// record the position of box
				if (firFlag && count == 0) {
					dataFlow.setBox1Pos(tempPos);
					dataFlow.setBoxDetect1(true);
					count++;
					firFlag = false;
				} else if (secFlag && count == 1) {
					dataFlow.setBox2Pos(tempPos);
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
