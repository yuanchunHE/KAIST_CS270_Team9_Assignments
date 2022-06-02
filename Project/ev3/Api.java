package pack;

import lejos.hardware.motor.Motor;
import lejos.robotics.RegulatedMotor;

public class Api {
    private TouchDetectThread touchDetectThread;

    public Api() {
        touchDetectThread = new TouchDetectThread();
        touchDetectThread.start();
    }

    public boolean ringTheBell() {
        // ring the bell
        // return true if robot succeeded to ring the bell, false otherwise
        // i.e., false means human rang the bell first
        RegulatedMotor motor = Motor.D;
	    motor.setSpeed((int) motor.getMaxSpeed());
        
        long startTime = System.currentTimeMillis();
        while (System.currentTimeMillis() - startTime < 300) {
            if (touchDetectThread.isTouched()) {
                return true;
            }
        }
        return false;        
    }

    public void flipOneCard() {
        // flip one card

        // step1: distribute one card
        RegulatedMotor motorA = Motor.A;
        motorA.setSpeed((int) motorA.getMaxSpeed() / 2);
        motorA.rotate(-190 * 2);
        motorA.flt();
        Delay.msDelay(100);
        motorA.rotate(90);

        // step2: flip one card
        RegulatedMotor motorB = Motor.B;
        motorB.setSpeed((int) motorB.getMaxSpeed() / 2);
        motorB.rotate(-135);
        motorB.flt();
        Delay.msDelay(100);
        motorB.rotate(135);
        motorB.flt();
    }

    public void stopAllThread() {
        touchDetectThread.Stop();
    }
}
