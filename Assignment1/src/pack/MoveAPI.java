package pack;

import lejos.hardware.motor.Motor;
import lejos.robotics.RegulatedMotor;
import lejos.utility.Delay;

public class MoveAPI {

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

	public void goBackward() {
		RegulatedMotor leftMotor = Motor.A;
		RegulatedMotor rightMotor = Motor.B;

		leftMotor.synchronizeWith(new RegulatedMotor[] { rightMotor });

		leftMotor.startSynchronization();
		leftMotor.forward();// ==robot go backward
		rightMotor.forward();
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

		Delay.msDelay(1080);

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

		Delay.msDelay(1080);

		leftMotor.startSynchronization();
		leftMotor.stop();
		rightMotor.stop();
		leftMotor.endSynchronization();
	}

	public boolean isFrontEmpty(DataFlow dataFlow){
		Coordinate roboPos=new Coordinate(dataFlow.getRobotPos().x,dataFlow.getRobotPos().y);
		Coordinate box1Pos=dataFlow.getBox1Pos();
		Coordinate box2Pos=dataFlow.getBox2Pos();
		
		Coordinate frontPos=new Coordinate();
		
		switch (dataFlow.getRobotPos().facingDirection) {
		case "+x":
			frontPos.x = roboPos.x + 1;
			frontPos.y = roboPos.y;
			break;
		case "-x":
			frontPos.x = roboPos.x - 1;
			frontPos.y = roboPos.y;
			break;
		case "+y":
			frontPos.x = roboPos.x;
			frontPos.y = roboPos.y + 1;
			break;
		case "-y":
			frontPos.x = roboPos.x;
			frontPos.y = roboPos.y - 1;
			break;
		}
		
		if(frontPos.equals(box1Pos)||frontPos.equals(box2Pos))
			return true;
		else
			return false;
	}

}
