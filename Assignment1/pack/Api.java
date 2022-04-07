package pack;

import lejos.hardware.motor.Motor;
import lejos.robotics.Color;
import lejos.robotics.RegulatedMotor;
import lejos.utility.Delay;
import java.util.Date;

public class Api {
	private static final boolean isSimulation = false;
	private SimulationApi simulApi;
	private BoxDetectThread boxDetectThread;
	private RedDetectThread redDetectThread;

	public Api() {
		if (isSimulation) {
			simulApi = new SimulationApi();
		}

		boxDetectThread = new BoxDetectThread();
		boxDetectThread.start();

		redDetectThread = new RedDetectThread();
		redDetectThread.start();
	}
	
	public void stopAllThread() {
		boxDetectThread.Stop();
		redDetectThread.Stop();
	}

	public int[] getStartPos() {
		if (isSimulation) {
			return simulApi.getStartPos();
		}

		while (true) {
			if (isBlue()) {
				return new int[] { 0, 0, 0 };
			}
			if (isYellow()) {
				return new int[] { 5, 3, 2 };
			}
		}
	}

	public void goForward2() {
		RegulatedMotor leftMotor = Motor.A;
		RegulatedMotor rightMotor = Motor.B;

		leftMotor.synchronizeWith(new RegulatedMotor[] { rightMotor });

		leftMotor.startSynchronization();
		leftMotor.setSpeed(750); rightMotor.setSpeed(750);
		leftMotor.endSynchronization();

		leftMotor.startSynchronization();
		leftMotor.setAcceleration(600); rightMotor.setAcceleration(600);
		leftMotor.endSynchronization();

		leftMotor.rotate(-360 * 3, true); rightMotor.rotate(-360 * 3, true);

		boolean checkLeft = false, checkRight = false;
		long leftTime = 0, rightTime = 0;

		while (checkLeft == false || checkRight == false) {
			if (checkLeft == false && redDetectThread.getLeftColor() == Color.BLACK) {
				leftTime = System.currentTimeMillis();
				checkLeft = true;
				System.out.println("LeftDetected");
			}
			if (checkRight == false && redDetectThread.getRightColor() == Color.BLACK) {
				rightTime = System.currentTimeMillis();
				checkRight = true;
				System.out.println("RightDetected");
			}
		}
		System.out.println(leftTime - rightTime);

		leftMotor.startSynchronization();
		leftMotor.stop(); rightMotor.stop();
		leftMotor.endSynchronization();

		int turnAngle = 360 * (int)(leftTime - rightTime) / 500;
		Delay.msDelay(550);
		leftMotor.startSynchronization();
		leftMotor.rotate(-turnAngle);
		rightMotor.rotate(turnAngle);
		leftMotor.endSynchronization();
		
		Delay.msDelay(550);
		leftMotor.startSynchronization();
		leftMotor.rotate(-360 * 1); rightMotor.rotate(-360 * 1);
		leftMotor.endSynchronization();
	}

	public void goForward() {
		if (isSimulation) {
			simulApi.goForward();
			return;
		}
		RegulatedMotor leftMotor = Motor.A;
		RegulatedMotor rightMotor = Motor.B;

		leftMotor.synchronizeWith(new RegulatedMotor[] { rightMotor });

		leftMotor.startSynchronization();
		leftMotor.setSpeed(750);// 750
		rightMotor.setSpeed(750);
		leftMotor.endSynchronization();

		leftMotor.startSynchronization();
		leftMotor.setAcceleration(600);
		rightMotor.setAcceleration(600);
		leftMotor.endSynchronization();

		// leftMotor.synchronizeWith(new RegulatedMotor[] { rightMotor });
		leftMotor.startSynchronization();
		leftMotor.rotate(-360 * 3);// left:+-570,580 //360 -> 8cm //270->6cm
									// //135->3cm //right:-+590
		rightMotor.rotate(-360 * 3);

		leftMotor.endSynchronization();

		//Delay.msDelay(3500);
		leftMotor.waitComplete();
		rightMotor.waitComplete();
		
		// RegulatedMotor leftMotor = Motor.A;
		// RegulatedMotor rightMotor = Motor.B;
		//
		// leftMotor.synchronizeWith(new RegulatedMotor[] { rightMotor });
		//
		// leftMotor.startSynchronization();
		// leftMotor.backward();// ==robot go forward
		// rightMotor.backward();
		// leftMotor.endSynchronization();
		//
		// // System.out.println(Motor.A.getMaxSpeed());//800-780
		// Delay.msDelay(3200);// 3300 < 4 cell
		//
		// leftMotor.startSynchronization();
		// leftMotor.stop();
		// rightMotor.stop();
		// leftMotor.endSynchronization();

		/*********
		 * second method to move*********** leftMotor.setSpeed(750);
		 * rightMotor.setSpeed(750);
		 * 
		 * leftMotor.startSynchronization(); leftMotor.setAcceleration(600);
		 * rightMotor.setAcceleration(600); leftMotor.rotate(4*360);
		 * rightMotor.rotate(4*360); leftMotor.endSynchronization();
		 * 
		 */
	}

	public void turnLeft() {
		if (isSimulation) {
			simulApi.turnLeft();
			return;
		}

		RegulatedMotor leftMotor = Motor.A;
		RegulatedMotor rightMotor = Motor.B;

		leftMotor.synchronizeWith(new RegulatedMotor[] { rightMotor });

		// time1600
		leftMotor.setSpeed(750);
		rightMotor.setSpeed(750);

		leftMotor.setAcceleration(600);
		rightMotor.setAcceleration(600);

		leftMotor.startSynchronization();
		leftMotor.backward();
		rightMotor.backward();
		leftMotor.endSynchronization();

		Delay.msDelay(550);

		leftMotor.startSynchronization();
		leftMotor.stop();
		rightMotor.stop();
		leftMotor.endSynchronization();

		leftMotor.waitComplete();
		rightMotor.waitComplete();

		leftMotor.startSynchronization();
		leftMotor.rotate(580);
		rightMotor.rotate(-580);
		leftMotor.endSynchronization();

		leftMotor.waitComplete();
		rightMotor.waitComplete();
		//Delay.msDelay(4000);
	}

	public void turnRight() {
		if (isSimulation) {
			simulApi.turnRight();
			return;
		}

		RegulatedMotor leftMotor = Motor.A;
		RegulatedMotor rightMotor = Motor.B;

		leftMotor.synchronizeWith(new RegulatedMotor[] { rightMotor });

		// time1600
		leftMotor.setSpeed(750);
		rightMotor.setSpeed(750);

		leftMotor.setAcceleration(600);
		rightMotor.setAcceleration(600);

		leftMotor.startSynchronization();
		leftMotor.backward();
		rightMotor.backward();
		leftMotor.endSynchronization();

		Delay.msDelay(550);

		leftMotor.startSynchronization();
		leftMotor.stop();
		rightMotor.stop();
		leftMotor.endSynchronization();

		leftMotor.waitComplete();
		rightMotor.waitComplete();

		leftMotor.startSynchronization();
		leftMotor.rotate(-580);
		rightMotor.rotate(580);
		leftMotor.endSynchronization();

		leftMotor.waitComplete();
		rightMotor.waitComplete();
		//Delay.msDelay(4000);
		//leftMotor.waitComplete();
		//rightMotor.waitComplete();
	}

	public boolean isBlocked() {
		if (isSimulation) {
			return simulApi.isBlocked();
		}

		if (boxDetectThread.getDistance() < 20.0f) {
			// System.out.println("Blocked!");
			return true;
		}
		return false;
	}

	public boolean isRed() {
		if (isSimulation) {
			return simulApi.isRed();
		}
		return redDetectThread.getColorID() == Color.RED;
	}

	public boolean isBlue() {
		return redDetectThread.getColorID() == Color.BLUE;
	}

	public boolean isYellow() {
		return redDetectThread.getColorID() == Color.YELLOW;
	}

	/*
	public int correctDirection() {
		// return 0:no need, 1:towards left, 2:towards right
		if (redDetectThread.getLeftColor() == Color.BLACK
				&& redDetectThread.getRightColor() != Color.BLACK)
			return 1;
		else if (redDetectThread.getLeftColor() != Color.BLACK
				&& redDetectThread.getRightColor() == Color.BLACK)
			return 2;
		else
			return 0;
	}
	*/
}

class SimulationApi {
	/* Maximum map size */
	private static final int maxMapSizeX = 5, maxMapSizeY = 3;

	/* Counterclockwise movement unit */
	private static final int dx[] = new int[] { 0, 1, 0, -1 };
	private static final int dy[] = new int[] { 1, 0, -1, 0 };

	/* Robot's current location */
	private int posX, posY, posDir;
	private int box1X, box1Y;
	private int box2X, box2Y;
	private int red1X, red1Y;
	private int red2X, red2Y;

	public SimulationApi() {
		posX = 0;
		posY = 0;
		posDir = 0;
		box1X = 0;
		box1Y = 1;
		box2X = 3;
		box2Y = 2;
		red1X = 0;
		red1Y = 2;
		red2X = 2;
		red2Y = 2;

		printMap();
	}

	private void printMap() {
		/*
		 * System.out.println("鈹屸攢鈹�鈹�鈹攢鈹�鈹�鈹攢鈹�鈹�鈹攢鈹�鈹�鈹攢鈹�鈹�鈹攢鈹�鈹�鈹�");
		 * for (int i=3; i>=0; i--){ for (int j=0; j<=5; j++){
		 * System.out.print("鈹�"); if (j == posX && i == posY) { if (posDir ==
		 * 0) System.out.print(" 鈻� "); else if(posDir == 1)
		 * System.out.print(" 鈻� "); else if(posDir == 2)
		 * System.out.print(" 鈻� "); else if(posDir == 3)
		 * System.out.print(" 鈼� "); } else if (j == box1X && i == box1Y)
		 * System.out.print(" 鈻� "); else if (j == box2X && i == box2Y)
		 * System.out.print(" 鈻� "); else if (j == red1X && i == red1Y)
		 * System.out.print(" 鈾� "); else if (j == red2X && i == red2Y)
		 * System.out.print(" 鈾� "); else System.out.print("   "); }
		 * System.out.println("鈹�"); if (i!=0) {
		 * System.out.println("鈹溾攢鈹�鈹�鈹尖攢鈹�鈹�鈹尖攢鈹�鈹�鈹尖攢鈹�鈹�鈹尖攢鈹�鈹�鈹尖攢鈹�鈹�鈹�"); }
		 * } System.out.println("鈹斺攢鈹�鈹�鈹粹攢鈹�鈹�鈹粹攢鈹�鈹�鈹粹攢鈹�鈹�鈹粹攢鈹�鈹�鈹粹攢鈹�鈹�鈹�");
		 * 
		 * try { Thread.sleep(300); } catch(InterruptedException e) {
		 * System.out.println("Error on sleep"); }
		 */
	}

	public int[] getStartPos() {
		return new int[] { posX, posY, posDir };
	}

	public void goForward() {
		posX += dx[posDir];
		posY += dy[posDir];

		printMap();
	}

	public void turnLeft() {
		posDir -= 1;
		if (posDir < 0)
			posDir = 3;

		printMap();
	}

	public void turnRight() {
		posDir += 1;
		if (posDir >= 4)
			posDir = 0;

		printMap();
	}

	public boolean isBlocked() {
		int tx = posX + dx[posDir];
		int ty = posY + dy[posDir];

		if (tx == box1X && ty == box1Y)
			return true;
		if (tx == box2X && ty == box2Y)
			return true;
		return false;
	}

	public boolean isRed() {
		if (posX == red1X && posY == red1Y)
			return true;
		if (posX == red2X && posY == red2Y)
			return true;
		return false;
	}
}