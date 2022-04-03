package pack;

import lejos.hardware.motor.Motor;
import lejos.robotics.Color;
import lejos.robotics.RegulatedMotor;
import lejos.utility.Delay;

public class Api {
    private BoxDetectThread boxDetectThread;
    private RedDetectThread redDetectThread;

    public Api() {
        boxDetectThread = new BoxDetectThread();
		boxDetectThread.start();
		
		redDetectThread = new RedDetectThread();
		redDetectThread.start();
    }

    public void goForward() {
        RegulatedMotor leftMotor = Motor.A;
		RegulatedMotor rightMotor = Motor.B;

		leftMotor.synchronizeWith(new RegulatedMotor[] { rightMotor });

		leftMotor.startSynchronization();
		leftMotor.backward();// ==robot go forward
		rightMotor.backward();
		leftMotor.endSynchronization();

		Delay.msDelay(3000);

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

		Delay.msDelay(3450);

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

		Delay.msDelay(3450);

		leftMotor.startSynchronization();
		leftMotor.stop();
		rightMotor.stop();
		leftMotor.endSynchronization();
    }
    public boolean isBlocked() {
        if (boxDetectThread.getDistance() < 11.0) return true;
        return false;
    }
    public boolean isRed() {
    	return redDetectThread.getColorID() == Color.RED;
    }
    public boolean isBlue() {
    	return redDetectThread.getColorID() == Color.BLUE;
    }
    public boolean isYellow() {
    	return redDetectThread.getColorID() == Color.YELLOW;
    }
    public boolean isBlack() {
    	return redDetectThread.getColorID() == Color.BLACK;
    }
    
}
