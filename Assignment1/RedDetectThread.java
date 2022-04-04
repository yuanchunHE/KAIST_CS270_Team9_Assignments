package pack;

import lejos.robotics.Color;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;

public class RedDetectThread extends Thread {
	// private DataFlow dataFlow;
	private EV3ColorSensor leftColorSensor;
	private EV3ColorSensor rightColorSensor;
	private boolean flag;
	private int frontColor;
	//private boolean 
	
	RedDetectThread() {
		flag = false;
		leftColorSensor = new EV3ColorSensor(SensorPort.S1);
		rightColorSensor = new EV3ColorSensor(SensorPort.S3);
		frontColor = 0;
	}

	public void Stop() {
		flag = true;
	}

	public void run() {
		while (!flag) {
			if (leftColorSensor.getColorID() == Color.RED
					&& rightColorSensor.getColorID() == Color.RED) {
				frontColor = leftColorSensor.getColorID();
			}
			else if (leftColorSensor.getColorID() == Color.BLACK
					&& rightColorSensor.getColorID() == Color.BLACK) {
				frontColor = leftColorSensor.getColorID();
			}
			else if (leftColorSensor.getColorID() == Color.BLUE
					&& rightColorSensor.getColorID() == Color.BLUE) {
				frontColor = leftColorSensor.getColorID();
			}
			else if (leftColorSensor.getColorID() == Color.YELLOW
					&& rightColorSensor.getColorID() == Color.YELLOW) {
				frontColor = leftColorSensor.getColorID();
			}
			else if (leftColorSensor.getColorID() == Color.GREEN
					&& rightColorSensor.getColorID() == Color.GREEN) {
				frontColor = leftColorSensor.getColorID();
			}
			else {
				frontColor = Color.WHITE;
			}
		}
	}
	
	public int getColorID() {
		return frontColor;
	}
}
