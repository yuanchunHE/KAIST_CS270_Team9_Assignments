package pack;

import lejos.robotics.Color;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;

public class RedDetectThread extends Thread {
	// private DataFlow dataFlow;
	private EV3ColorSensor leftColorSensor;
	// private EV3ColorSensor rightColorSensor;
	private boolean flag;
	private int frontColor;
	private int[] colorIdList = new int[]{ Color.RED, Color.WHITE, Color.BLUE, Color.YELLOW };

	// private int leftColor;
	// private int rightColor;

	// private boolean

	RedDetectThread() {
		flag = false;
		leftColorSensor = new EV3ColorSensor(SensorPort.S3);
		// rightColorSensor = new EV3ColorSensor(SensorPort.S3);
		frontColor = 0;
		// leftColor = 0;
		// rightColor = 0;
	}

	public void Stop() {
		flag = true;
	}

	public void run() {
		while (!flag) {
			int colorId = leftColorSensor.getColorID();
			
			for (int i : colorIdList) {
				if (i == colorId) {
					frontColor = colorId;
				}
			}
			/*
			 * leftColor = leftColorSensor.getColorID(); rightColor =
			 * rightColorSensor.getColorID(); if (leftColor == Color.RED &&
			 * rightColor == Color.RED) { frontColor = leftColor; } else if
			 * (leftColor == Color.BLACK && rightColor == Color.BLACK) {
			 * frontColor = leftColor; } else if (leftColor == Color.BLUE &&
			 * rightColor == Color.BLUE) { frontColor = leftColor; } else if
			 * (leftColor == Color.YELLOW && rightColor == Color.YELLOW) {
			 * frontColor = leftColor; } else if (leftColor == Color.GREEN &&
			 * rightColor == Color.GREEN) { frontColor = leftColor; } else {
			 * frontColor = Color.WHITE; }
			 */
		}
	}

	public int getColorID() {
		return frontColor;
	}
	/*
	 * public int getLeftColor(){ //System.out.println(leftColor);
	 * //System.out.println(leftColor == Color.BLACK); return leftColor; }
	 * public int getRightColor(){ return rightColor; }
	 */
}
