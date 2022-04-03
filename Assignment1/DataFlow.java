

public class DataFlow {

	private boolean RedDetect1 = false;
	private boolean RedDetect2 = false;
	private boolean BoxDetect1 = false;
	private boolean BoxDetect2 = false;

	private Coordinate Red1Pos;
	private Coordinate Red2Pos;
	private Coordinate Box1Pos;
	private Coordinate Box2Pos;

	private CoordinateSys robotPos;
	
	private int state = 1;// 0:infer start point, 1:go forward, 2:turn, 3:go
							// back to start point

	public DataFlow() {
		//System.out.println("DataFlow Started!");
	}

	public CoordinateSys getRobotPos() {
		return robotPos;
	}

	public void setRobotPos(CoordinateSys robotPos) {
		this.robotPos = robotPos;
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

	public Coordinate getRed1Pos() {
		return Red1Pos;
	}
	public void setRed1Pos(Coordinate red1Pos) {
		Red1Pos = red1Pos;
	}

	public Coordinate getRed2Pos() {
		return Red2Pos;
	}

	public void setRed2Pos(Coordinate red2Pos) {
		Red2Pos = red2Pos;
	}

	public Coordinate getBox1Pos() {
		return Box1Pos;
	}

	public void setBox1Pos(Coordinate box1Pos) {
		Box1Pos = box1Pos;
	}

	public Coordinate getBox2Pos() {
		return Box2Pos;
	}

	public void setBox2Pos(Coordinate box2Pos) {
		Box2Pos = box2Pos;
	}

}
