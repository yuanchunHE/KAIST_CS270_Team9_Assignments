//package pack;
//
//import lejos.robotics.Color;
//import lejos.hardware.BrickFinder;
//import lejos.hardware.Keys;
//import lejos.hardware.ev3.EV3;
////import lejos.hardware.BrickFinder;
////import lejos.hardware.Keys;
////import lejos.hardware.ev3.EV3;
//import lejos.hardware.port.Port;
//import lejos.hardware.port.SensorPort;
//import lejos.hardware.sensor.EV3ColorSensor;
//
//public class ColorSensors {
//
//	private String leftColor;
//	private String rightColor;
//
//	private static EV3ColorSensor leftColorSensor;
//	private static EV3ColorSensor rightColorSensor;
//	
//	public static void main(String[] args){
//		new ColorSensors();
//	}
//	
//	ColorSensors() {
//		EV3 ev3 = (EV3) BrickFinder.getLocal();
//		Keys keys = ev3.getKeys();
//		
//		leftColorSensor = new EV3ColorSensor(SensorPort.S1);
//		rightColorSensor = new EV3ColorSensor(SensorPort.S3);
//		
//		do {
//			//System.out.println("leftColor :" + leftColor);
//
//			int colorIDL = leftColorSensor.getColorID();
//			int colorIDR = rightColorSensor.getColorID();
//
//			// set L color
//			switch (colorIDL) {
//			case Color.BLACK:
//				leftColor = "BLACK";
//				break;
//			case Color.RED:
//				leftColor = "RED";
//				break;
//			case Color.BLUE:
//				leftColor = "BLUE";
//				break;
//			case Color.YELLOW:
//				leftColor = "YELLOW";
//				break;
//			case Color.WHITE:
//				leftColor = "WHITE";
//				break;
//			case Color.GREEN:
//				leftColor = "GREEN";
//				break;
//			default:
//				leftColor = "?";
//				break;
//			}
//
//			// set R color
//			switch (colorIDR) {
//			case Color.BLACK:
//				rightColor = "BLACK";
//				break;
//			case Color.RED:
//				rightColor = "RED";
//				break;
//			case Color.BLUE:
//				rightColor = "BLUE";
//				break;
//			case Color.YELLOW:
//				rightColor = "YELLOW";
//				break;
//			case Color.WHITE:
//				rightColor = "WHITE";
//				break;
//			case Color.GREEN:
//				leftColor = "GREEN";
//				break;
//			default:
//				rightColor = "?";
//				break;
//			}
//		} while (keys.getButtons()!=Keys.ID_DOWN);
//		leftColorSensor.close();
//		rightColorSensor.close();
//	}
//
//	public String getLeftColor() {
//		return leftColor;
//	}
//
//	public String getRightColor() {
//		return rightColor;
//	}
//
//}
