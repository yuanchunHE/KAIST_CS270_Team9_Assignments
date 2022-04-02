package pack;

import lejos.hardware.port.SensorPort;

public class RedDetect extends Thread {
	private DataFlow dataFlow;
	private ColorSensors colorSensors;

	public RedDetect(DataFlow df) {
		dataFlow = df;
		colorSensors = new ColorSensors(SensorPort.S1, SensorPort.S3);
	}

	public void run() {
		int count = 0;// num of detected red cells
		boolean firFlag = true;
		boolean secFlag = true;

		while (true) {
			if (colorSensors.getLeftColor() == "RED"
					&& colorSensors.getRightColor() == "RED") {
				if (firFlag && count == 0) {
					dataFlow.setRedDetect1(true);
					count++;
				} else if (secFlag && count == 1) {
					dataFlow.setRedDetect2(true);
					if (dataFlow.isBoxDetect1() && dataFlow.isBoxDetect2()
							&& dataFlow.isRedDetect1()
							&& dataFlow.isRedDetect2()) {
						dataFlow.setState(3);
					}
					secFlag = false;
				}
			}
//			try {
//				Thread.sleep(2000);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
		}
	}
}
