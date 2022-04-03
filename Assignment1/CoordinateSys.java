package pack;

public class CoordinateSys extends Coordinate {
	int facingDirection;

	CoordinateSys() {
		facingDirection = 0;
	}
	CoordinateSys(int c,int a,int b) {
		super(a,b);
		facingDirection = c;
	}
}
