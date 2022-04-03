package pack;

import lejos.hardware.motor.Motor;
import lejos.robotics.RegulatedMotor;

public class GoForward extends Thread {
	DataFlow dataFlow;

	// leftMotor.synchronizeWith(new RegulatedMotor[] { rightMotor });

	public GoForward(DataFlow df) {
		dataFlow = df;
	}

	public void run() {
		RegulatedMotor leftMotor = Motor.A;
		RegulatedMotor rightMotor = Motor.B;
		
		System.out.println("GoForward Started!");
		
		leftMotor.synchronizeWith(new RegulatedMotor[] { rightMotor });

		while (true) {
			if (dataFlow.getState() == 1) {
				// go forward
				leftMotor.startSynchronization();
				leftMotor.backward();
				rightMotor.backward();
				leftMotor.endSynchronization();
			}
		}
	}

}