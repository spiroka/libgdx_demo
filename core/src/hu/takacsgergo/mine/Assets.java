package hu.takacsgergo.mine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by spiroka on 9/2/2016.
 */
public class Assets {
    public static Texture background;
    public static Texture mine;
    public static Texture player;

    private Assets() {}

    public static void load() {
        background = new Texture(Gdx.files.internal("tile.png"));
        mine = new Texture(Gdx.files.internal("mine.png"));
        player = new Texture(Gdx.files.internal("player.png"));
    }
}
