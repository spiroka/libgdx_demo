package hu.takacsgergo.mine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by spiroka on 10/9/2016.
 */
public class GameScreen extends ScreenAdapter {
    private OrthographicCamera camera;
    private Mine game;
    private World world;
    private WorldRenderer worldRenderer;

    public GameScreen(Mine game) {
        this.game = game;
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 12, 12 * (h/w));

        world = new World();
        worldRenderer = new WorldRenderer(game.getBatch(), world);

        Gdx.input.setInputProcessor(new InputHandler(camera, world));
    }

    @Override
    public void render (float delta) {
        update(delta);
        draw();
    }

    private void update(float delta) {
        world.update(delta);
    }

    private void draw() {
        SpriteBatch batch = game.getBatch();
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();

        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        worldRenderer.render();
        batch.end();
    }
}
