package test;

import lejos.hardware.BrickFinder;
import lejos.hardware.Keys;
import lejos.hardware.ev3.EV3;
import lejos.hardware.motor.Motor;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.SampleProvider;
import lejos.utility.Delay;

public class test_motor {
	// private static EV3IRSensor sensor;
	private static TestTouch touchSensor;

	public static void main(String[] args) {
		// startSynchronization();
		EV3 ev3 = (EV3) BrickFinder.getLocal();
		// TextLCD lcd = ev3.getTextLCD();
		Keys keys = ev3.getKeys();

		touchSensor = new TestTouch();
		touchSensor.start();
		
		do {
			if (touchSensor.getTouchValue() == 1) {
				System.out.println("touched");
			} else {
				System.out.println("not touched");
			}
			keys.waitForAnyPress();
		} while (keys.getButtons() != Keys.ID_ESCAPE);
		
		touchSensor.Stop();
		// testHand();
		// for (int i = 0; i < 5; i++) {
		// testDistributer();
		// testFlipor();
		// }
	}

	public static void testHand() {
		RegulatedMotor motor = Motor.A;

		motor.setSpeed((int) motor.getMaxSpeed());
		motor.rotate(-360 * 24 - 130);
		motor.flt();
		// for (int i = 0; i < 24; i++) {
		// motor.rotate(-360 * 3);
		// motor.flt();
		// Delay.msDelay(100);
		// }
	}

	public static void testDistributer() {

		RegulatedMotor motor = Motor.A;

		motor.setSpeed((int) motor.getMaxSpeed() / 2);
		// motor.setAcceleration(100);
		for (int i = 0; i < 1; i++) {
			motor.rotate(-190 * 2);
			motor.flt();
			Delay.msDelay(100);
			motor.rotate(90);
		}
	}

	public static void testFlipor() {
		RegulatedMotor motor = Motor.B;

		motor.setSpeed((int) motor.getMaxSpeed() / 2);

		motor.rotate(-135);
		motor.flt();

		Delay.msDelay(100);

		motor.rotate(135);
		motor.flt();
	}
}
