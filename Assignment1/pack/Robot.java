package pack;

public class Robot {
	public static void main(String[] args) {
		Api api = new Api();
		Explorer explorer = new Explorer(api);
		
		int[] startPos = api.getStartPos();
		while(!(explorer.run(startPos[0], startPos[1], startPos[2]))){};

    	System.out.println("Finish.");
    	System.out.print(explorer.getResult());
	}
}