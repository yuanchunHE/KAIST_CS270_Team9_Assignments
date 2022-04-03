package pack;

import lejos.hardware.motor.Motor;
import lejos.robotics.RegulatedMotor;
import lejos.utility.Delay;

public class Api {
    private BoxDetectThread boxDetectThread;

    public Api() {
        boxDetectThread = new BoxDetectThread();
		boxDetectThread.start();
    }

    public void goForward() {
        RegulatedMotor leftMotor = Motor.A;
		RegulatedMotor rightMotor = Motor.B;

		leftMotor.synchronizeWith(new RegulatedMotor[] { rightMotor });

		leftMotor.startSynchronization();
		leftMotor.backward();// ==robot go forward
		rightMotor.backward();
		leftMotor.endSynchronization();

		Delay.msDelay(1000);

		leftMotor.startSynchronization();
		leftMotor.stop();
		rightMotor.stop();
		leftMotor.endSynchronization();
    }
    public void turnLeft() {
        RegulatedMotor leftMotor = Motor.A;
		RegulatedMotor rightMotor = Motor.B;

		leftMotor.synchronizeWith(new RegulatedMotor[] { rightMotor });

		leftMotor.startSynchronization();
		leftMotor.forward();
		rightMotor.backward();
		leftMotor.endSynchronization();

		Delay.msDelay(1150);

		leftMotor.startSynchronization();
		leftMotor.stop();
		rightMotor.stop();
		leftMotor.endSynchronization();
    }
    public void turnRight() {
        RegulatedMotor leftMotor = Motor.A;
		RegulatedMotor rightMotor = Motor.B;

		leftMotor.synchronizeWith(new RegulatedMotor[] { rightMotor });

		leftMotor.startSynchronization();
		leftMotor.backward();
		rightMotor.forward();
		leftMotor.endSynchronization();

		Delay.msDelay(1150);

		leftMotor.startSynchronization();
		leftMotor.stop();
		rightMotor.stop();
		leftMotor.endSynchronization();
    }
    public boolean isBlocked() {
        if (boxDetectThread.getDistance() < 11.0) return true;
        return false;
    }
}
