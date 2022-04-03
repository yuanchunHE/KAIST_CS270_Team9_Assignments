//package pack;
//
//import lejos.hardware.port.SensorPort;
//
//public class Start extends Thread {
//	DataFlow dataFlow;
//	private ColorSensors colorSensors;
//
//	Start(DataFlow df) {
//		dataFlow = df;
//		colorSensors = new ColorSensors(SensorPort.S1, SensorPort.S3);
//	}
//
//	public void run() {
//		while (dataFlow.getState()==0) {
//			System.out.println("left color: " + colorSensors.getLeftColor());
//
//			// yellow stands for (0,0)
//			if (colorSensors.getLeftColor().equals("YELLOW")
//					|| colorSensors.getRightColor().equals("YELLOW")) {
//				// set (0,0) to the start point
//				//
//				System.out.println("THIS IS (0,0).");
//				dataFlow.setState(1);
//			} else if (colorSensors.getLeftColor() == "BLUE"
//					|| colorSensors.getRightColor() == "BLUE") {
//				// set (5,3) to the start point
//				//
//				System.out.println("THIS IS (5,3).");
//				dataFlow.setState(1);
//			}
//		}
//	}
//}