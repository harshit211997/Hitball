package com.sdsmdg.cycle.objects.buttons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.sdsmdg.cycle.gameworld.GameWorld;

public class StateButton extends Button{

    public enum ButtonState {
        ON, OFF
    }

    ButtonState state;

    public StateButton(GameWorld world, float x, float y, float width, float height, TextureRegion regionOn, TextureRegion regionOff, ButtonState state) {
        super(world, x, y, width, height, regionOn, regionOff);
        this.state = state;
        if(isStateOn()) {
            current = regionOn;
        }
    }

    @Override
    public void onTouchDown() {
        alreadyTouchedDown = true;
    }

    @Override
    public void onTouchUp() {
        if(alreadyTouchedDown) {
            listener.onClick();
            changeState();
        }
        alreadyTouchedDown = false;
    }

    @Override
    public void onTouchDragged() { }

    @Override
    public void onRemoveTouch() { }

    public boolean isStateOn() {
        if(state == ButtonState.OFF) {
            return false;
        }
        else {
            return true;
        }
    }

    public void changeState() {
        if(!isStateOn()) {
            state = ButtonState.ON;
            current = regionOn;
            Gdx.app.log("StateButton", "ON");
        } else {
            state = ButtonState.OFF;
            current = regionOff;
            Gdx.app.log("StateButton", "OFF");
        }

    }
}
