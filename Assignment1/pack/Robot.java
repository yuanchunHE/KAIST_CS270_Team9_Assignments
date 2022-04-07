package pack;

import lejos.hardware.BrickFinder;
import lejos.hardware.Keys;
import lejos.hardware.ev3.EV3;
import lejos.utility.Delay;

public class Robot {
	public static void main(String[] args) {
		Api api = new Api();
		Explorer explorer = new Explorer(api);

		EV3 ev3 = (EV3) BrickFinder.getLocal();
		Keys keys = ev3.getKeys();

		int[] startPos = api.getStartPos();
		explorer.run(startPos[0], startPos[1], startPos[2]);
		// api.goForward2();

		System.out.println("Finish.");
		// System.out.print(explorer.getResult());
		// Delay.msDelay(10000);

		keys.waitForAnyPress();
		while (keys.getButtons() == Keys.ID_ESCAPE)
			api.stopAllThread();
	}
}