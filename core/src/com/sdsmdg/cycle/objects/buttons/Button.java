package com.sdsmdg.cycle.objects.buttons;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.sdsmdg.cycle.chelpers.AssetLoader;
import com.sdsmdg.cycle.gameworld.GameWorld;

public class Button {

    float width, height;
    float MAX_WIDTH, MAX_HEIGHT;
    Vector2 position = new Vector2();
    Rectangle rectangle;
    GameWorld myWorld;
    TextureRegion regionOn, regionOff, current;
    int id;//This determines which type of button is it
    float theta;
    Vector2 initialPos = new Vector2();
    OnClickListener listener;

    /*
    This keeps track of whether the button was initially touchedDown
    as it should not call onClick if the user touches some part of screen
    but raises his finger above the button
    */
    boolean alreadyTouchedDown = false;

    public Button(GameWorld world, float x, float y, float width, float height, TextureRegion regionOn, TextureRegion regionOff, int id) {
        this.height = height;
        this.regionOn = regionOn;
        this.regionOff = regionOff;
        this.width = width;
        this.position.x = x;
        this.position.y = y;
        this.rectangle = new Rectangle(position.x - width / 2, position.y - height / 2, width, height);
        this.myWorld = world;
        this.id = id;

        this.theta = 90 * (float) Math.random();

        MAX_HEIGHT = height;//Initial height is the maximum height
        MAX_WIDTH = width;//Similarly, the width

        current = regionOff;
        initialPos.set(x, y);
    }

    public void setOnClickListener(OnClickListener listener) {
        this.listener = listener;
    }

    public boolean isTouched(int x, int y) {
        rectangle.set(position.x - width / 2, position.y - height / 2, width, height);
        return rectangle.contains(x, y);
    }

    public void update(float delta) {
        theta += delta;
        width = MAX_WIDTH / 50 * (float) Math.sin(4 * theta) + 49 * MAX_WIDTH / 50;
        height = MAX_HEIGHT / 50 * (float) Math.sin(4 * theta) + 49 * MAX_HEIGHT / 50;
    }

    public void onDraw(SpriteBatch batcher) {
        batcher.draw(current, position.x - width / 2, position.y - height / 2, width, height);
    }

    public void onTouchDown() {
        current = regionOn;
        alreadyTouchedDown = true;
    }

    public void onTouchDragged() {
        if(alreadyTouchedDown) {
            current = regionOn;
        }
    }

    public void onTouchUp() {

        if(alreadyTouchedDown) {
            listener.onClick();
            AssetLoader.buttonClick.play();
            current = regionOff;
        }

        alreadyTouchedDown = false;
    }

    /*
    This function is used by input handler,
    it is used for the case when the user touches down the button, and drags it away,
    this method helps input handler to make the button "unpressed" at that time
    */
    public void onRemoveTouch() {
        current = regionOff;
    }

    public float getHeight() {
        return height;
    }

    public boolean isAlreadyTouchedDown() {
        return alreadyTouchedDown;
    }

    public TextureRegion getRegion() {
        return current;
    }

    public float getWidth() {
        return width;
    }

    public Vector2 getPosition() {
        return position;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public void reset() {
        position.set(initialPos.x, initialPos.y);
    }

    public void setPosition(float x, float y) {
        this.position.set(x, y);
    }

    public interface OnClickListener {
        public void onClick();
    }
}
