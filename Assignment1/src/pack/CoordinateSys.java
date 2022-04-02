package pack;

public class CoordinateSys extends Coordinate {
	String facingDirection;

	CoordinateSys() {
		facingDirection = "+x";
	}
	CoordinateSys(String dir,int a,int b) {
		super(a,b);
		facingDirection = "+x";
	}
}
