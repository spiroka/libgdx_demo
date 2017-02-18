package hu.takacsgergo.mine;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by spiroka on 10/9/2016.
 */
public abstract class GameObject {
    public Vector2 position;
    protected int width;
    protected int height;
    private Rectangle bounds;

    public GameObject() {}

    public GameObject(float x, float y, int width, int height) {
        position = new Vector2(x, y);
        this.width = width;
        this.height = height;
        bounds = new Rectangle();
    }

    public Rectangle getBounds() {
        bounds.set(position.x, position.y, width, height);

        return bounds;
    }
}
