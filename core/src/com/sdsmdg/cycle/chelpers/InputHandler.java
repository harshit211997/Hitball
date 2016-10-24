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
            if(myWorld.getPlayReady().isTouched(screenX, screenY)) {
                myWorld.getPlayReady().onTouchDown();
            }
            if(myWorld.getAchievementButton().isTouched(screenX, screenY)) {
                myWorld.getAchievementButton().onTouchDown();
            }
            if(myWorld.getLeaderBoardButton().isTouched(screenX, screenY)) {
                myWorld.getLeaderBoardButton().onTouchDown();
            }
            if(myWorld.getInfoButton().isTouched(screenX, screenY)) {
                myWorld.getInfoButton().onTouchDown();
            }
        } else if(myWorld.isOver()) {
            if(myWorld.getPlayButton().isTouched(screenX, screenY)) {
                myWorld.getPlayButton().onTouchDown();
            }
            if(myWorld.getAchievementButton().isTouched(screenX, screenY)) {
                myWorld.getAchievementButton().onTouchDown();
            }
            if(myWorld.getLeaderBoardButton().isTouched(screenX, screenY)) {
                myWorld.getLeaderBoardButton().onTouchDown();
            }
        }

        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if (myWorld.isRunning())
            myWorld.getBat().onTouchUp();
        else if(myWorld.isOver()) {
            if(myWorld.getPlayButton().isTouched(screenX, screenY)) {
                myWorld.getPlayButton().onTouchUp();
            }
            if(myWorld.getAchievementButton().isTouched(screenX, screenY)) {
                myWorld.getAchievementButton().onTouchUp();
            }
            if(myWorld.getLeaderBoardButton().isTouched(screenX, screenY)) {
                myWorld.getLeaderBoardButton().onTouchUp();
            }
        } else if(myWorld.isReady()) {
            if(myWorld.getPlayReady().isTouched(screenX, screenY)) {
                myWorld.getPlayReady().onTouchUp();
            }
            if(myWorld.getAchievementButton().isTouched(screenX, screenY)) {
                myWorld.getAchievementButton().onTouchUp();
            }
            if(myWorld.getLeaderBoardButton().isTouched(screenX, screenY)) {
                myWorld.getLeaderBoardButton().onTouchUp();
            }
            if(myWorld.getInfoButton().isTouched(screenX, screenY)) {
                myWorld.getInfoButton().onTouchUp();
            }
        }
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        if(myWorld.isOver()) {
            if(!myWorld.getPlayButton().isTouched(screenX, screenY)) {
                myWorld.getPlayButton().onRemoveTouch();
            }else {
                myWorld.getPlayButton().onTouchDragged();
            }
            if(!myWorld.getAchievementButton().isTouched(screenX, screenY)) {
                myWorld.getAchievementButton().onRemoveTouch();
            }
            else {
                myWorld.getAchievementButton().onTouchDragged();
            }
            if(!myWorld.getLeaderBoardButton().isTouched(screenX, screenY)) {
                myWorld.getLeaderBoardButton().onRemoveTouch();
            }
            else {
                myWorld.getLeaderBoardButton().onTouchDragged();
            }
        } else if(myWorld.isReady()) {
            if(!myWorld.getPlayReady().isTouched(screenX, screenY)) {
                myWorld.getPlayReady().onRemoveTouch();
            }else {
                myWorld.getPlayReady().onTouchDragged();
            }
            if(!myWorld.getAchievementButton().isTouched(screenX, screenY)) {
                myWorld.getAchievementButton().onRemoveTouch();
            }else{
                myWorld.getAchievementButton().onTouchDragged();
            }
            if(!myWorld.getLeaderBoardButton().isTouched(screenX, screenY)) {
                myWorld.getLeaderBoardButton().onRemoveTouch();
            }else {
                myWorld.getLeaderBoardButton().onTouchDragged();
            }
            if(!myWorld.getInfoButton().isTouched(screenX, screenY)) {
                myWorld.getInfoButton().onRemoveTouch();
            }else{
                myWorld.getInfoButton().onTouchDragged();
            }
        }
        return true;
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
