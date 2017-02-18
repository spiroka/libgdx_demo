package hu.takacsgergo.mine;

/**
 * Created by spiroka on 10/9/2016.
 */
public class World {
    public static final int WIDTH = 10 * Tile.WIDTH;
    public static final int HEIGHT = 3 * Tile.HEIGHT;
    public static final int SURFACE_OFFSET = Tile.HEIGHT;

    private Tile[][] tiles;
    private Player player;

    public World() {
        tiles = new Tile[WIDTH/Tile.WIDTH][HEIGHT/Tile.HEIGHT];
        createTiles();
        createPlayer();
    }

    public void update(float delta) {
        for(Tile[] row : tiles) {
            for (Tile tile : row) {
                tile.update(delta);
            }
        }
    }

    public void createTiles() {
        for(int y = 0; y < HEIGHT; y += Tile.HEIGHT) {
            for(int x = 0; x < WIDTH; x += Tile.WIDTH) {
                Tile tile = new Tile((float)x, (float)y);
                tiles[x/Tile.WIDTH][y/Tile.HEIGHT] = tile;
            }
        }
    }

    public void createPlayer() {
        player = new Player(13, 6);
        tiles[4][2].setState(Tile.State.DUG);
    }

    public Tile[][] getTiles() {
        return tiles;
    }

    public Player getPlayer() {
        return player;
    }
}
