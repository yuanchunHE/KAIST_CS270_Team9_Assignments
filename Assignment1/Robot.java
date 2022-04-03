package pack;

import lejos.hardware.BrickFinder;
import lejos.hardware.Keys;
import lejos.hardware.ev3.EV3;
import lejos.hardware.lcd.TextLCD;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.Color;

public class Robot {
	private static DataFlow dataFlow;
	private static GoForward goForward;
	private static BoxDetect boxDetect;
	private static RedDetect redDetect;
	private static Explorer explorer;
	private static MoveAPI moveAPI;

	public static void main(String[] args) {
		EV3 ev3 = (EV3) BrickFinder.getLocal();
		TextLCD lcd = ev3.getTextLCD();
		Keys keys = ev3.getKeys();

		// data start to flow
		moveAPI = new MoveAPI();
		dataFlow = new DataFlow();
		// to get start pos and facingDir
		dataFlow.setRobotPos(toGetStartPoint(dataFlow, 1));// 10->10s

		boxDetect = new BoxDetect(dataFlow);
		redDetect = new RedDetect(dataFlow);
		goForward = new GoForward(dataFlow);
		explorer = new Explorer(moveAPI, dataFlow);

		boxDetect.start();
		redDetect.start();
		goForward.start();
		explorer.start();
		
		while (keys.getButtons() != Keys.ID_ESCAPE) {

		}
		System.out.println("Finish!");
        //System.out.print(explorer.getResult());
		//lcd.drawString("Finish", 0, 0);
	}

	public static CoordinateSys toGetStartPoint(DataFlow df, int second) {
		long startTime = System.currentTimeMillis();
		long endTime = startTime + 1000 * second;

		EV3ColorSensor leftColorSensor;
		EV3ColorSensor rightColorSensor;

		leftColorSensor = new EV3ColorSensor(SensorPort.S1);
		rightColorSensor = new EV3ColorSensor(SensorPort.S3);

		// System.out.println("toGetStartPoint Started!");
		CoordinateSys startPos = new CoordinateSys();
		// do {
		// distinguish start point
		if (leftColorSensor.getColorID() == Color.YELLOW
				|| rightColorSensor.getColorID() == Color.YELLOW) {
			// set (0,0) to the start point
			startPos.x = 0;
			startPos.y = 0;
			startPos.facingDirection = 1;
			// System.out.println("THIS IS (0,0).");
			dataFlow.setState(1);
		} else if (leftColorSensor.getColorID() == Color.BLUE
				|| rightColorSensor.getColorID() == Color.BLUE) {
			// set (5,3) to the start point
			startPos.x = 5;
			startPos.y = 3;
			startPos.facingDirection = 0;
			// System.out.println("THIS IS (5,3).");
			dataFlow.setState(1);
		}
		// } while (System.currentTimeMillis() < endTime);

		leftColorSensor.close();
		rightColorSensor.close();

		return startPos;
	}
}