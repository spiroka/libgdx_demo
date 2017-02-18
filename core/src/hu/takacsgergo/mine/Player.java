package hu.takacsgergo.mine;

/**
 * Created by spiroka on 11/1/2016.
 */
public class Player extends GameObject {
    public enum State {
        IDLE,
        MOVING
    }

    public static final int WIDTH = 1;
    public static final int HEIGHT = 1;

    private State state;
    private float stateTime;

    public Player(float x, float y) {
        super(x, y, WIDTH, HEIGHT);
        this.state = State.IDLE;
        stateTime = 0;
    }
}
