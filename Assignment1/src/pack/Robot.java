package pack;

import lejos.hardware.BrickFinder;
import lejos.hardware.Keys;
import lejos.hardware.ev3.EV3;
import lejos.hardware.lcd.TextLCD;

public class Robot {
	private static DataFlow dataFlow;
	private static GoForward goForward;
	private static BoxDetect boxDetect;
	private static RedDetect redDetect;
	private static Start start;
	private static Turn turn;

	public static void main(String[] args) {
		EV3 ev3 = (EV3) BrickFinder.getLocal();
		TextLCD lcd = ev3.getTextLCD();
		Keys keys = ev3.getKeys();

		//Coordinate coordinate = new Coordinate();// color

		dataFlow = new DataFlow();
		goForward = new GoForward(dataFlow);
		goForward.start();
		boxDetect = new BoxDetect(dataFlow);
		boxDetect.start();
		redDetect = new RedDetect(dataFlow);
		redDetect.start();
		start = new Start(dataFlow);
		start.start();
		turn = new Turn(dataFlow);
		turn.start();

		while (keys.getButtons() != Keys.ID_ESCAPE) {

		}

		lcd.drawString("Finish", 0, 0);
	}

}