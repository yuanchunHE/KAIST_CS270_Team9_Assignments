public class Explorer {
    /* Maximum map size */
    private static final Integer maxMapSizeX = 5, maxMapSizeY = 3;
    
    /* Robot's current location */
    private Integer posX, posY, posDir;

    /* Recorded map (0 = unconfirmed, 1 = empty. 2 = box) */
    private Integer map[][];
    
    /* Api */
    private Api api;
    
    public Explorer(Api _api) {
        api = _api;
    }

    private void moveDirect(Integer x, Integer y) {

    }

    private void dfs(Integer x, Integer y, Integer dir) {
        map[x][y] = 1;
        api.goForward();
    }

    public void run(Integer x, Integer y, Integer dir) {
        // initialization
        posX = x; posY = y; posDir = dir;
        map = new Integer[maxMapSizeX + 1][maxMapSizeY + 1];
        
        // dfs
        dfs(x, y, dir);
    }
}
