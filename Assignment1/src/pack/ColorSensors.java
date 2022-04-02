package pack;

import lejos.robotics.Color;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3ColorSensor;

public class ColorSensors {

	private String leftColor;
	private String rightColor;

	ColorSensors(Port port1, Port port2) {

		do {
			System.out.println("leftColor :"+leftColor);
			EV3ColorSensor leftColorSensor = new EV3ColorSensor(port1);
			EV3ColorSensor rightColorSensor = new EV3ColorSensor(port2);

			int colorIDL = leftColorSensor.getColorID();
			int colorIDR = rightColorSensor.getColorID();

			// set L color
			switch (colorIDL) {
			case Color.BLACK:
				leftColor = "BLACK";
				break;
			case Color.RED:
				leftColor = "RED";
				break;
			case Color.BLUE:
				leftColor = "BLUE";
				break;
			case Color.YELLOW:
				leftColor = "YELLOW";
				break;
			case Color.WHITE:
				leftColor = "WHITE";
				break;
			default:
				leftColor = "?";
				break;
			}

			// set R color
			switch (colorIDR) {
			case Color.BLACK:
				rightColor = "BLACK";
				break;
			case Color.RED:
				rightColor = "RED";
				break;
			case Color.BLUE:
				rightColor = "BLUE";
				break;
			case Color.YELLOW:
				rightColor = "YELLOW";
				break;
			case Color.WHITE:
				rightColor = "WHITE";
				break;
			default:
				rightColor = "?";
				break;
			}

			leftColorSensor.close();
			rightColorSensor.close();
		} while (true);

	}

	public String getLeftColor() {
		return leftColor;
	}

	public String getRightColor() {
		return rightColor;
	}

}
