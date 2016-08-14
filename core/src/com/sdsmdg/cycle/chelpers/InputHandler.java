package com.sdsmdg.cycle.chelpers;

import com.badlogic.gdx.InputProcessor;
import com.sdsmdg.cycle.gameworld.GameWorld;

public class InputHandler implements InputProcessor {

    private GameWorld myWorld;
    private static final String TAG = InputHandler.class.getSimpleName();

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
        if (myWorld.isRunning()) {
            myWorld.getBat().onTouchDown();
        } else if(myWorld.isReady()){
            myWorld.setGameStateRunning();
        } else if(myWorld.isOver()) {
            if(myWorld.getReplayButton().isTouched(screenX, screenY)) {
                myWorld.getReplayButton().onTouchDown();
            }
        }
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if (myWorld.isRunning())
            myWorld.getBat().onTouchUp();
        else if(myWorld.isOver()) {
            if(myWorld.getReplayButton().isTouched(screenX, screenY)) {
                myWorld.getReplayButton().onTouchUp();
            }
        }
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        if(myWorld.isOver()) {
            if(!myWorld.getReplayButton().isTouched(screenX, screenY)) {
                myWorld.getReplayButton().onRemoveTouch();
            }else {
                myWorld.getReplayButton().onTouchDown();
            }
        }
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
