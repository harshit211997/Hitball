package com.sdsmdg.cycle.objects;

import com.badlogic.gdx.math.Vector2;

public class Bat {

    private static final int UP = 0;
    private static final int DOWN = 1;

    boolean isRotating = false;
    int height, width;
    Vector2 position;
    float rotation = 10;
    float w = -200;

    public Bat(int height, int width, Vector2 position) {
        this.height = height;
        this.width = width;
        this.position = position;
    }

    public void update(float delta) {
        if(isRotating) {
            rotation += w * delta;
        }
        /*
        if the bat tries to rotate below its initial position
        we set isRotating = false, so that it doesn't keep rotating
        or if above its maximum rotating position,
        we rotate it in opposite direction
         */
        if(aboveBounds()) {
            w = 200;
        }
        else if(belowBounds()) {
            stopRotation();
        }
    }

    public void onClick() {
        startRotation(UP);
    }

    public void startRotation(int direction) {
        isRotating = true;
        w = -200;
    }

    public void stopRotation() {
        isRotating = false;
    }

    public boolean belowBounds() {
        if(rotation > 10) {
            return true;
        }
        return false;
    }

    public boolean aboveBounds() {
        if(rotation < -20) {
            return true;
        }
        return false;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public float getRotation() {
        return rotation;
    }

    public Vector2 getPosition() {
        return position;
    }
}
