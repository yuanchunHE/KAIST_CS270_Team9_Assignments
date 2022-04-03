public class Api {
    /* Maximum map size */
    private static final int maxMapSizeX = 5, maxMapSizeY = 3;

    /* Counterclockwise movement unit */
    private static final int dx[] = new int[]{ 0, 1, 0, -1 };
    private static final int dy[] = new int[]{ 1, 0, -1, 0 };

    /* Robot's current location */
    private int posX, posY, posDir;
    private int box1X, box1Y;
    private int box2X, box2Y;

    public Api() {
        posX = 0; posY = 0; posDir = 0;
        box1X = 0; box1Y = 1;
        box2X = 3; box2Y = 2;

        printMap();
    }

    private void printMap() {
        System.out.println("┌───┬───┬───┬───┬───┬───┐");
        for (int i=3; i>=0; i--){
            for (int j=0; j<=5; j++){
                System.out.print("│");
                if (j == box1X && i == box1Y) System.out.print(" ■ ");
                else if (j == box2X && i == box2Y) System.out.print(" ■ ");
                else if (j == posX && i == posY) {
                    if (posDir == 0) System.out.print(" △ ");
                    else if(posDir == 1) System.out.print(" ▷ ");
                    else if(posDir == 2) System.out.print(" ▽ ");
                    else if(posDir == 3) System.out.print(" ◁ ");
                }
                else System.out.print("   ");
            }
            System.out.println("│");
            if (i!=0) {
                System.out.println("├───┼───┼───┼───┼───┼───┤");
            }
        }
        System.out.println("└───┴───┴───┴───┴───┴───┘");

        try {
            Thread.sleep(300);
        } catch(InterruptedException e) {
            System.out.println("Error on sleep");
        }
    }

    public void goForward() {
        posX += dx[posDir];
        posY += dy[posDir];

        printMap();
    }
    public void turnLeft() {
        posDir -= 1;
        if (posDir < 0) posDir = 3;

        printMap();
    }
    public void turnRight() {
        posDir += 1;
        if (posDir >= 4) posDir = 0;

        printMap();
    }
    public boolean isBlocked() {
        int tx = posX + dx[posDir];
        int ty = posY + dy[posDir];

        if (tx == box1X && ty == box1Y) return true;
        if (tx == box2X && ty == box2Y) return true;
        return false;
    }

    /*
        turnLeft를 두 번 하는 것 보다 뒤로 가는 것이 better??
        거리 확인 센서를 회전 시키는 것은 어떨까???
    */
}
