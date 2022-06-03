package test_motor;

import lejos.hardware.BrickFinder;
import lejos.hardware.Keys;
import lejos.hardware.ev3.EV3;
import lejos.utility.Delay;

public class Robot {
    private static final int maxRound = 10;

	public static void main(String[] args) {
        Api api = new Api();

        FruitDetectThread fruitDetectThread = new FruitDetectThread();
        fruitDetectThread.start();

        int round = 1;
        boolean isRobotTurn = true;
        boolean needWait = false;
        int cacheCardStatus = 0;

		EV3 ev3 = (EV3) BrickFinder.getLocal();
        Keys keys = ev3.getKeys();
        
        while (round <= maxRound) {        	
        	if (keys.getButtons() == Keys.ID_ESCAPE) break;
        	Delay.msDelay(500); // TODO: delete this
        	// System.out.printf("robot turn? %b\n", isRobotTurn);
        	// System.out.printf("need wait? %b\n", needWait);
            if (fruitDetectThread.areThereFiveFruit()) {
                // System.out.println("Ring the bell!");
                boolean success = api.ringTheBell();
                // System.out.printf("success? %b\n", success);
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
        api.stopAllThread();
        fruitDetectThread.Stop();
	}
}
