package hu.takacsgergo.mine;

import com.badlogic.gdx.utils.Timer;

/**
 * Created by spiroka on 9/2/2016.
 */
public class Tile extends GameObject {
    public enum State {
        UNDUG,
        DIGGING,
        DUG
    }

    public static final int WIDTH = 3;
    public static final int HEIGHT = 3;

    private State state;
    private float stateTime;

    public Tile(float x, float y) {
        super(x, y, WIDTH, HEIGHT);
        this.state = State.UNDUG;
        stateTime = 0;
    }

    public void update(float delta) {
        stateTime += delta;
    }

    public void dig() {
        state = State.DIGGING;
        stateTime = 0;
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                state = State.DUG;
                stateTime = 0;
            }
        }, 5);
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public float getStateTime() {
        return stateTime;
    }
}
