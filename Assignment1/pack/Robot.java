package pack;

import lejos.utility.Delay;

public class Robot {
	public static void main(String[] args) {
		Api api = new Api();
		Explorer explorer = new Explorer(api);
		
		int[] startPos = api.getStartPos();
		explorer.run(startPos[0], startPos[1], startPos[2]);
		//api.goForward2();
		
    	System.out.println("Finish.");
    	//System.out.print(explorer.getResult());
    	Delay.msDelay(10000);
    	api.stopAllThread();
	}
}