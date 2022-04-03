package pack;

import lejos.robotics.Color;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;

public class RedDetect extends Thread {
	private DataFlow dataFlow;
	private EV3ColorSensor leftColorSensor;
	private EV3ColorSensor rightColorSensor;

	RedDetect(DataFlow df) {
		dataFlow = df;
		leftColorSensor = new EV3ColorSensor(SensorPort.S1);
		rightColorSensor= new EV3ColorSensor(SensorPort.S3);
	}

	public void run() {
		int count = 0;// num of detected red cells
		boolean firFlag = true;
		boolean secFlag = true;

		System.out.println("RedDetect Started!");

		while (true) {
			// calculate the position of the red cell
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

			// record the position of red cell
			if (leftColorSensor.getColorID() == Color.RED
					&& rightColorSensor.getColorID() == Color.RED) {
				if (firFlag && count == 0) {
					dataFlow.setRed1Pos(tempPos);
					dataFlow.setRedDetect1(true);
					count++;
				} else if (secFlag && count == 1) {
					dataFlow.setRed2Pos(tempPos);
					dataFlow.setRedDetect2(true);
					if (dataFlow.isBoxDetect1() && dataFlow.isBoxDetect2()
							&& dataFlow.isRedDetect1()
							&& dataFlow.isRedDetect2()) {
						dataFlow.setState(3);
					}
					secFlag = false;
				}
			}
			// try {
			// Thread.sleep(2000);
			// } catch (InterruptedException e) {
			// e.printStackTrace();
			// }
		}
	}
}