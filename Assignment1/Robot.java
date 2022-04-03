package pack;

public class Robot {
	public static void main(String[] args) {
		// check start position
		
		Api api = new Api();
		Explorer explorer = new Explorer(api);

		while (true) {
			if (api.isBlue()) {
		        explorer.run(0, 0, 0);
		        break;
			}
			else if (api.isYellow()) {
				explorer.run(5, 3, 2);
				break;
			}
		}

        System.out.println("This is result of explorer");
        System.out.print(explorer.getResult());
	}
}