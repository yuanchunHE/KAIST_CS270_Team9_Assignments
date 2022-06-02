package pack;

public class FruitDetectThread extends Thread {
    FruitDetectThread() {
	int status = 0;
    }

	public void Stop() {
    }

	public void run() {
    }

    public int getStatus() {
        return status;
    }

    public boolean areThereFiveFruit() {
	String str = new String();
	str = "1234"; // TODO: get status from socket
	boolean success = Character.compare(str.charAT(0), '5') == 0) ||
		Character.compare(str.charAT(1), '5') == 0) ||
		Character.compare(str.charAT(2), '5') == 0) ||
		Character.compare(str.charAT(3), '5') == 0)
	    
        return success;
    }
}
