package hu.takacsgergo.mine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by spiroka on 8/29/2016.
 */
public class InputHandler implements InputProcessor {
    private Vector3 lastTouch = new Vector3();
    private OrthographicCamera camera;
    private World world;
    private Tile[][] tiles;
    private Tile touchedTile = null;

    public InputHandler(OrthographicCamera camera, World world) {
        this.camera = camera;
        this.world = world;
        tiles = world.getTiles();
    }

    @Override
    public boolean touchDown (int screenX, int screenY, int pointer, int button) {
        if(button != Input.Buttons.LEFT || pointer > 0) return false;

        lastTouch.set(screenX, screenY, 0);

        Vector3 touchPos = lastTouch.cpy();
        camera.unproject(touchPos);

        if(((int)touchPos.y)/Tile.HEIGHT < tiles[0].length) {
            touchedTile = tiles[(int)touchPos.x/Tile.WIDTH][(int)touchPos.y/Tile.HEIGHT];
        }

        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if(button != Input.Buttons.LEFT || pointer > 0) return false;

        if(touchedTile != null && touchedTile.getState() == Tile.State.UNDUG) {
            touchedTile.dig();
            touchedTile = null;
        }

        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        // handle camera movement
        Vector3 newTouch = new Vector3(screenX, screenY, 0);
        double distance = lastTouch.dst(newTouch);
        Vector3 delta = newTouch.cpy().sub(lastTouch);
        lastTouch = newTouch;
        delta.nor();

        delta.scl((float)distance);

        float newX = camera.position.x - (delta.x * 0.005f);
        float newY = camera.position.y + (delta.y * 0.005f);

        float xMax = World.WIDTH - camera.viewportWidth/2;
        float xMin = 0 + camera.viewportWidth/2;
        float yMax = World.HEIGHT + World.SURFACE_OFFSET - camera.viewportHeight/2;
        float yMin = 0 + camera.viewportHeight/2;

        if(newX > xMax) {
            newX = xMax;
        } else if(newX < xMin) {
            newX = xMin;
        }

        if(newY > yMax) {
            newY = yMax;
        } else if(newY < yMin) {
            newY = yMin;
        }

        camera.position.x = newX;
        camera.position.y = newY;

        // handle tiles
        Vector3 touchPos = newTouch.cpy();
        camera.unproject(touchPos);

        if(touchedTile != null && !touchedTile.getBounds().contains(touchPos.x, touchPos.y)) {
            touchedTile = null;
        }

        return true;
    }

    @Override
    public boolean keyDown(int keycode) {
       return false;
    }

    @Override
    public boolean keyUp(int keycode) {
       return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
