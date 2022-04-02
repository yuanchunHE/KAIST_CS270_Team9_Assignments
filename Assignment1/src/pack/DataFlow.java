package pack;

public class DataFlow {

	private boolean RedDetect1 = false;
	private boolean RedDetect2 = false;
	private boolean BoxDetect1 = false;
	private boolean BoxDetect2 = false;
	
	private int state=0;//0:infer start point, 1:go forward, 2:turn, 3:go back to start point
	
	public DataFlow(){
		
	}

	public boolean isRedDetect1() {
		return RedDetect1;
	}

	public void setRedDetect1(boolean redDetect1) {
		RedDetect1 = redDetect1;
	}

	public boolean isRedDetect2() {
		return RedDetect2;
	}

	public void setRedDetect2(boolean redDetect2) {
		RedDetect2 = redDetect2;
	}

	public boolean isBoxDetect1() {
		return BoxDetect1;
	}

	public void setBoxDetect1(boolean boxDetect1) {
		BoxDetect1 = boxDetect1;
	}

	public boolean isBoxDetect2() {
		return BoxDetect2;
	}

	public void setBoxDetect2(boolean boxDetect2) {
		BoxDetect2 = boxDetect2;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}
	
	
}
