package com.sdsmdg.cycle.chelpers;

import com.badlogic.gdx.InputProcessor;
import com.sdsmdg.cycle.gameworld.GameWorld;
import com.sdsmdg.cycle.objects.Button;

public class InputHandler implements InputProcessor{

    private GameWorld myWorld;

    public InputHandler(GameWorld world) {
        myWorld = world;
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
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if(myWorld.isRunning())
            myWorld.getBat().onTouchDown();
        else if(myWorld.isReady()) {

            Button playButton = myWorld.getPlayButton();
            if(playButton.getRectangle().contains(screenX, screenY)) {
                playButton.onClick();
            }

        }
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if(myWorld.isRunning())
            myWorld.getBat().onTouchUp();
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
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
