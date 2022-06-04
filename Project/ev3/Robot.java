package test_motor;

import lejos.hardware.BrickFinder;
import lejos.hardware.Keys;
import lejos.hardware.ev3.EV3;
import lejos.utility.Delay;

public class Robot {
    private static final int maxRound = 5;

	public static void main(String[] args) {
        Api api = new Api();

        FruitDetectThread fruitDetectThread = new FruitDetectThread();
        fruitDetectThread.start();

        int round = 1, winRobot = 0, winHuman = 0;
        boolean isRobotTurn = true;
        boolean needWait = false;
        int cacheCardStatus = 0;

		EV3 ev3 = (EV3) BrickFinder.getLocal();
        Keys keys = ev3.getKeys();
        
        while (round <= maxRound) {
        	if (keys.getButtons() == Keys.ID_ESCAPE) break;

            if (fruitDetectThread.areThereFiveFruit()) {
                boolean success = api.ringTheBell();
                if (success) {
                    System.out.println("Round " + round + " : Robot win!!");
                    winRobot += 1;
                } else {
                    System.out.println("Round " + round + " : You win!!");
                    winHuman += 1;
                }
                
                System.out.println("Total score [Robot : Human] = " + winRobot + " : " + winHuman);
                System.out.println("The robot is waiting for the next round");
                api.waitUntilRoundEnd();
                round += 1;
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
