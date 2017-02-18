package hu.takacsgergo.mine;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Mine extends Game {
    private SpriteBatch batch;

    @Override
    public void create() {
        batch = new SpriteBatch();
        Assets.load();
        setScreen(new GameScreen(this));
    }

	@Override
	public void dispose () {
        batch.dispose();
	}

    public SpriteBatch getBatch() {
        return batch;
    }
}