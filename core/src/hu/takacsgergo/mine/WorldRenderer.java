package hu.takacsgergo.mine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;

import static com.badlogic.gdx.math.MathUtils.sin;

/**
 * Created by spiroka on 10/9/2016.
 */
public class WorldRenderer {
    private SpriteBatch batch;
    private World world;
    private ShaderProgram blinkShader;

    public WorldRenderer(SpriteBatch batch, World world) {
        this.batch = batch;
        this.world = world;
        String vertexShader = Gdx.files.internal("shaders/mine.vert").readString();
        String fragmentShader = Gdx.files.internal("shaders/mine.frag").readString();
        blinkShader = new ShaderProgram(vertexShader, fragmentShader);
    }

    public void render() {
        renderTiles();
        renderPlayer();
    }

    private void renderTiles() {
        Texture texture;
        Tile[][] tiles = world.getTiles();
        for(Tile[] row : tiles) {
            for(Tile tile : row) {
                if(tile.getState() == Tile.State.DIGGING) {
                    blinkShader.begin();
                    blinkShader.setUniformf("u_red", (sin(tile.getStateTime() * 5) + 1) * 0.05f);
                    blinkShader.end();

                    batch.setShader(blinkShader);
                }

                texture = tile.getState() == Tile.State.DUG ? Assets.mine : Assets.background;
                texture.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);

                batch.draw(texture, tile.position.x, tile.position.y, Tile.WIDTH, Tile.HEIGHT, 0, 1, 1, 0);
                batch.setShader(null);
            }
        }
    }

    private void renderPlayer() {
        Texture texture = Assets.player;
        Player player = world.getPlayer();

        batch.draw(texture, player.position.x, player.position.y, Player.WIDTH, Player.HEIGHT, 0, 1, 1, 0);
    }
}
