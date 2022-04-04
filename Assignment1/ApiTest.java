//public class Api {
//    /* Maximum map size */
//    private static final int maxMapSizeX = 5, maxMapSizeY = 3;
//
//    /* Counterclockwise movement unit */
//    private static final int dx[] = new int[]{ 0, 1, 0, -1 };
//    private static final int dy[] = new int[]{ 1, 0, -1, 0 };
//
//    /* Robot's current location */
//    private int posX, posY, posDir;
//    private int box1X, box1Y;
//    private int box2X, box2Y;
//
//    public Api() {
//        posX = 0; posY = 0; posDir = 0;
//        box1X = 0; box1Y = 1;
//        box2X = 3; box2Y = 2;
//
//        printMap();
//    }
//
//    private void printMap() {
//        System.out.println("鈹屸攢鈹�鈹�鈹攢鈹�鈹�鈹攢鈹�鈹�鈹攢鈹�鈹�鈹攢鈹�鈹�鈹攢鈹�鈹�鈹�");
//        for (int i=3; i>=0; i--){
//            for (int j=0; j<=5; j++){
//                System.out.print("鈹�");
//                if (j == box1X && i == box1Y) System.out.print(" 鈻� ");
//                else if (j == box2X && i == box2Y) System.out.print(" 鈻� ");
//                else if (j == posX && i == posY) {
//                    if (posDir == 0) System.out.print(" 鈻� ");
//                    else if(posDir == 1) System.out.print(" 鈻� ");
//                    else if(posDir == 2) System.out.print(" 鈻� ");
//                    else if(posDir == 3) System.out.print(" 鈼� ");
//                }
//                else System.out.print("   ");
//            }
//            System.out.println("鈹�");
//            if (i!=0) {
//                System.out.println("鈹溾攢鈹�鈹�鈹尖攢鈹�鈹�鈹尖攢鈹�鈹�鈹尖攢鈹�鈹�鈹尖攢鈹�鈹�鈹尖攢鈹�鈹�鈹�");
//            }
//        }
//        System.out.println("鈹斺攢鈹�鈹�鈹粹攢鈹�鈹�鈹粹攢鈹�鈹�鈹粹攢鈹�鈹�鈹粹攢鈹�鈹�鈹粹攢鈹�鈹�鈹�");
//
//        try {
//            Thread.sleep(300);
//        } catch(InterruptedException e) {
//            System.out.println("Error on sleep");
//        }
//    }
//
//    public void goForward() {
//        posX += dx[posDir];
//        posY += dy[posDir];
//
//        printMap();
//    }
//    public void turnLeft() {
//        posDir -= 1;
//        if (posDir < 0) posDir = 3;
//
//        printMap();
//    }
//    public void turnRight() {
//        posDir += 1;
//        if (posDir >= 4) posDir = 0;
//
//        printMap();
//    }
//    public boolean isBlocked() {
//        int tx = posX + dx[posDir];
//        int ty = posY + dy[posDir];
//
//        if (tx == box1X && ty == box1Y) return true;
//        if (tx == box2X && ty == box2Y) return true;
//        return false;
//    }
//
//    /*
//        turnLeft毳� 霊� 氩� 頃橂姅 瓴� 氤措嫟 霋る 臧�電� 瓴冹澊 better??
//        瓯半Μ 頇曥澑 靹检劀毳� 須岇爠 鞁滍偆電� 瓴冹潃 鞏措枿旯�???
//    */
//}
