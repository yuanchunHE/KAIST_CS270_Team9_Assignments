package pack;

import java.util.Stack;

public class Explorer {
    /* Maximum map size */
    private static final int maxMapSizeX = 5, maxMapSizeY = 3;
    private static final int INF = 99999999;

    /* Counterclockwise movement unit */
    private static final int dx[] = new int[]{ 0, 1, 0, -1 };
    private static final int dy[] = new int[]{ 1, 0, -1, 0 };
    
    /* Robot's current location */
    private int posX, posY, posDir;
    private int countDetectedBox, countDetectedRed;

    /* Recorded map */
    private int mapBox[][]; // (0 = unconfirmed , 1 = empty , 2 = box )
    private int mapRed[][]; // (0 = unconfirmed , 1 = white , 2 = red )
    
    /* Api */
    private Api api;
   
    /* Constructer */
    public Explorer(Api _api) {
        api = _api;
    }

    /* Using dijkstra, move to destination with minimal rotation */
    private void moveDirect(int x, int y) {
        int dp[][][] = new int[maxMapSizeX + 1][maxMapSizeY + 1][4];
        int path[][][] = new int[maxMapSizeX + 1][maxMapSizeY + 1][4];
        boolean check[][][] = new boolean[maxMapSizeX + 1][maxMapSizeY + 1][4];

        for (int i = 0; i <= maxMapSizeX; i++) {
            for (int j = 0; j <= maxMapSizeY; j++) {
                for(int k = 0; k < 4; k++) {
                    dp[i][j][k] = INF;
                }
                if (countDetectedBox >= 2 && mapBox[i][j] == 0) {
                    mapBox[i][j] = 1;
                }
            }
        }
        dp[posX][posY][posDir] = 0;

        while (true) {// (dp[x][y][0] + dp[x][y][1] + dp[x][y][2] + dp[x][y][3] >= INF * 4) {
            int tmpX = -1, tmpY = -1, tmpDir = -1;
            int minCost = INF;
            
            for (int i = 0; i <= maxMapSizeX; i++) {
                for (int j = 0; j <= maxMapSizeY; j++) {
                    for(int k = 0; k < 4; k++) {
                        if (check[i][j][k] == false && minCost > dp[i][j][k]) {
                            minCost = dp[i][j][k];
                            tmpX = i; tmpY = j; tmpDir = k;
                        }
                    }
                }
            }

            if (minCost >= INF) break;
            check[tmpX][tmpY][tmpDir] = true;

            int tx = tmpX, ty = tmpY, tdir = tmpDir;

            tdir = (tmpDir + 1) % 4;
            if (dp[tx][ty][tdir] > minCost + 1) {
                dp[tx][ty][tdir] = minCost + 1;
                path[tx][ty][tdir] = 1;
            }

            tdir = (tmpDir + 3) % 4;
            if (dp[tx][ty][tdir] > minCost + 1) {
                dp[tx][ty][tdir] = minCost + 1;
                path[tx][ty][tdir] = 3;
            }

            tdir = tmpDir;
            tx = tmpX + dx[tmpDir];
            ty = tmpY + dy[tmpDir];
            if (0 <= tx && tx <= maxMapSizeX && 0 <= ty && ty <= maxMapSizeY) {
                if (mapBox[tx][ty] == 1 && dp[tx][ty][tdir] > minCost) {
                    dp[tx][ty][tdir] = minCost;
                    path[tx][ty][tdir] = 0;
                }
            }
        }

        int tx = x, ty = y, tdir = 0;
        Stack<String> stack = new Stack<>();
        for (int i = 0; i < 4; i++) {
            if (dp[tx][ty][tdir] > dp[tx][ty][i]) {
                tdir = i;
            }
        }

        while (tx != posX || ty != posY || tdir != posDir) {
            if (path[tx][ty][tdir] == 0) {
                stack.push("goForward");
                tx -= dx[tdir];
                ty -= dy[tdir];
            }
            else if (path[tx][ty][tdir] == 1) {
                stack.push("turnRight");
                tdir = (tdir + 3) % 4;
            }
            else if (path[tx][ty][tdir] == 3) {
                stack.push("turnLeft");
                tdir = (tdir + 1) % 4;
            }
        }
        while (!stack.empty()) {
            String str = stack.pop();
            if (str == "goForward") {
                api.goForward();
                posX += dx[posDir];
                posY += dy[posDir];
            }
            else if (str == "turnRight") {
                api.turnRight();
                posDir = (posDir + 1) % 4;
            }
            else if (str == "turnLeft") {
                api.turnLeft();
                posDir = (posDir + 3) % 4;
            }
        }
    }

    /* DFS */
    private void dfs(int x, int y) {
        if (countDetectedRed < 2 && mapRed[x][y] == 0) {
            moveDirect(x, y);
            if (api.isRed()) {
                mapRed[x][y] = 2;
                countDetectedRed += 1;
                printPos(x, y, "R");
            }
            else {
                mapRed[x][y] = 1;
            }
        }

        while (true) {
            if (countDetectedBox >= 2 && countDetectedRed >= 2) break;

            boolean keepDfs = false;

            for (int i = 0; i < 4; i++) {
                int tx = x + dx[i];
                int ty = y + dy[i];
                if (0 <= tx && tx <= maxMapSizeX && 0 <= ty && ty <= maxMapSizeY) {
                    if (countDetectedBox < 2 && mapBox[tx][ty] == 0) {
                        keepDfs = true;
                    }
                    if (countDetectedRed < 2 && mapRed[tx][ty] == 0) {
                        keepDfs = true;
                    }
                }
            }

            if (!keepDfs) break;

            int ddir[] = new int[]{ 0, 1, 3, 2 };
            for (int i = 0; i < 4; i++) {
                int tdir = (posDir + ddir[i]) % 4;
                int tx = x + dx[tdir];
                int ty = y + dy[tdir];
                
                if (0 <= tx && tx <= maxMapSizeX && 0 <= ty && ty <= maxMapSizeY) {
                    if (countDetectedBox < 2 && mapBox[tx][ty] == 0) {
                        moveDirect(x, y);
                        if (ddir[i] == 1) {
                            api.turnRight();
                            posDir = (posDir + 1) % 4;
                        }
                        else if (ddir[i] == 3) {
                            api.turnLeft();
                            posDir = (posDir + 3) % 4;
                        }
                        else if (ddir[i] == 4) {
                            api.turnRight();
                            api.turnRight();
                            posDir = (posDir + 2) % 4;
                        }

                        if (api.isBlocked()) {
                            mapBox[tx][ty] = 2;
                            mapRed[tx][ty] = 1;
                            countDetectedBox += 1;
                            printPos(tx, ty, "B");
                        }
                        else {
                            mapBox[tx][ty] = 1;
                            dfs(tx, ty);
                        }
                        break;
                    }
                    if (countDetectedRed < 2 && mapRed[tx][ty] == 0) {
                        dfs(tx, ty);
                        break;
                    }
                }
            }
        }
    }

    public void run(int x, int y, int dir) {
        // initialization
        posX = x; posY = y; posDir = dir;
        mapBox = new int[maxMapSizeX + 1][maxMapSizeY + 1];
        mapRed = new int[maxMapSizeX + 1][maxMapSizeY + 1];
        countDetectedBox = 0;
        countDetectedRed = 0;
        
        // dfs
        dfs(x, y);

        // end
        moveDirect(x, y);
    }

    private void printPos(int x, int y, String z) {
        System.out.println("(" + x + "," + y + "," + z + ")");
    }

    public String getResult() {
        String str = "";
        
        for (int x = 0; x <= maxMapSizeX; x++) {
            for (int y = 0; y <= maxMapSizeY; y++) {
                if (mapBox[x][y] == 2) {
                    str = str + "(" + x + "," + y + "," + "B)\n";
                }
                if (mapRed[x][y] == 2) {
                    str = str + "(" + x + "," + y + "," + "R)\n";
                }
            }
        }

        return str;
    }
}