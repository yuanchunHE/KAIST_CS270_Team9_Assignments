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
    private int countDetectedBox;

    /* Recorded map (0 = unconfirmed, 1 = empty. 2 = box) */
    private int map[][];
    
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
                if (map[tx][ty] == 1 && dp[tx][ty][tdir] > minCost) {
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
        map[x][y] = 1;

        while (true) {
            if (countDetectedBox >= 2) break;

            boolean keepDfs = false;

            for (int i = 0; i < 4; i++) {
                int tx = x + dx[i];
                int ty = y + dy[i];
                if (0 <= tx && tx <= maxMapSizeX && 0 <= ty && ty <= maxMapSizeY) {
                    if (map[tx][ty] == 0) {
                        keepDfs = true;
                    }
                }
            }

            if (!keepDfs) break;
            moveDirect(x, y);

            int ddir[] = new int[]{ 0, 1, 3, 2 };
            for (int i = 0; i < 4; i++) {
                int tdir = (posDir + ddir[i]) % 4;
                int tx = x + dx[tdir];
                int ty = y + dy[tdir];
                
                if (0 <= tx && tx <= maxMapSizeX && 0 <= ty && ty <= maxMapSizeY) {
                    if (map[tx][ty] == 0) {
                        if (ddir[i] == 1) {
                            api.turnRight();
                            posDir = (posDir + 1) % 4;
                        }
                        else if (ddir[i] == 3) {
                            api.turnLeft();
                            posDir = (posDir + 3) % 4;
                        }

                        if (api.isBlocked()) {
                            map[tx][ty] = 2;
                            countDetectedBox += 1;
                        }
                        else {
                            dfs(tx, ty);
                        }
                        break;
                    }
                }
            }
        }
    }

    public void run(int x, int y, int dir) {
        // initialization
        posX = x; posY = y; posDir = dir;
        map = new int[maxMapSizeX + 1][maxMapSizeY + 1];
        countDetectedBox = 0;
        
        // dfs
        dfs(x, y);
        System.out.println(map[2][1]);

        for (int i = 0; i <= maxMapSizeX; i++) {
            for (int j = 0; j <= maxMapSizeY; j++) {
                if (map[i][j] == 0) map[i][j] = 1;
            }
        }
        moveDirect(0, 0);
    }

    public String getResult() {
        String str = "";
        
        for (int x = 0; x <= maxMapSizeX; x++) {
            for (int y = 0; y <= maxMapSizeY; y++) {
                if (map[x][y] == 2) {
                    str = str + "(" + x + "," + y + "," + "B)\n";
                }
            }
        }

        return str;
    }
}
