package com.sdsmdg.cycle.objects;

import com.badlogic.gdx.math.Vector2;

public class Bat {

    private static final int UP = 0;
    private static final int DOWN = 1;
    private static final int MAX_ROTATION = 10;
    private static final int MIN_ROTATION = -15;
    private static final int MAX_W = 150;
    int direction = UP;

    int handleWidth;
    int originX, originY;
    boolean isRotating = false;
    int height, width;
    Vector2 position;
    float rotation = MAX_ROTATION;
    float w = -MAX_W;

    public Bat(int width, int height, Vector2 position) {
        this.height = height;
        this.width = width;
        this.position = position;
        originX = (int)position.x + width / 4;
        originY = (int)position.y + height / 2;
        handleWidth = (340 * width) / 990;
    }

    public void update(float delta) {
        if(isRotating) {
            rotate(delta);
        }
        /*
        if the bat tries to rotate below its initial position
        we set isRotating = false, so that it doesn't keep rotating
        or if above its maximum rotating position,
        we rotate it in opposite direction
         */
    }

    public void onTouchDown() {
        isRotating = true;
        direction = UP;
    }

    public void onTouchUp() {
        isRotating = true;
        direction = DOWN;
    }

    public void rotate(float delta) {
        if(direction == UP && !aboveBounds()) {
            rotation += w * delta;
        }
        else if(direction == DOWN && !belowBounds()) {
            rotation -= w * delta;
        }
        else {
            isRotating = false;
        }
    }

    public boolean belowBounds() {
        if(rotation > MAX_ROTATION) {
            return true;
        }
        return false;
    }

    public boolean isRotating() {
        return isRotating;
    }

    public boolean aboveBounds() {
        if(rotation < MIN_ROTATION) {
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

    public float getW() {
        return w;
    }

    public int getOriginX() {
        return originX;
    }

    public int getOriginY() {
        return originY;
    }

    public int getWidthWithoutHandle() {
        return width - handleWidth;
    }

    //returns x coordinate of center of body part of the bat
    public float getCenterBodyX() {
        return getPosition().x + getHandleWidth() + getWidthWithoutHandle() / 2;
    }

    //returns x coordinate of center of handle part of the bat
    public float getCenterHandleX() {
        return position.x + handleWidth / 2;
    }

    //returns y coordinate of center of handle part of the bat
    public float getCenterHandleY() {
        return position.y + height / 2;
    }

    //returns y coordinate of center of body part of the bat
    public float getCenterBodyY() {
        return getPosition().y + getHeight() / 2;
    }

    public int getHandleWidth() {
        return handleWidth;
    }
}
