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
	status = 1234; // TODO: get status from socket
        return status;
    }

    public boolean areThereFiveFruit() {
	int tempStatus = status;
	strawberry = tempStatus / 1000;
	if (strawberry == 5) {
		return true;
	}
	tempStatus -= strawberry * 1000;
	banana = tempStatus / 100;
	if (banana == 5) {
		return true;
	}
	tempStatus -= banana * 100;
	lime = tempStatus / 10;
	if (lime == 5) {
		return true;
	}
	plum = tempStatus - lime * 10;
	if (plum == 5) {
		return true;
	}
        return false;
    }
}
