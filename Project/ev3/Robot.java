package pack;

import lejos.hardware.BrickFinder;
import lejos.hardware.Keys;
import lejos.hardware.ev3.EV3;
import lejos.utility.Delay;

public class Robot {
    private static final int maxRound = 1;

	public static void main(String[] args) {
        Api api = new Api();
        FruitDetectThread fruitDetectThread = new FruitDetectThread();
        TouchDetectThread touchDetectThread = new TouchDetectThread();

        int round = 1;
        boolean isRobotTurn = true;
        boolean needWait = false;
        int cacheCardStatus = 0;

        while (round <= maxRound) {
            if (fruitDetectThread.areThereFiveFruit()) {
                boolean success = api.ringTheBell();
                // check who win -> success=True means robot first
                round += 1;
                // winner first ?

            } else if (isRobotTurn) {
                // turn of robot
                if (!needWait) {
                    cacheCardStatus = fruitDetectThread.getStatus();
                    api.flipOneCard();
                    needWait = true;
                } else if (fruitDetectThread.getStatus() != cacheCardStatus) {
                    needWait = false;
                    isRobotTurn = false;
                }

            } else {
                // turn of human
                if (!needWait) {
                    cacheCardStatus = fruitDetectThread.getStatus();
                    needWait = true;
                } else if (fruitDetectThread.getStatus() != cacheCardStatus) {
                    needWait = false;
                    isRobotTurn = true;
                }
            }
        }

        // stop all thread
        fruitDetectThread.Stop();
        touchDetectThread.Stop();
	}
}
