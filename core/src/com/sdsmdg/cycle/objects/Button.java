package com.sdsmdg.cycle.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.sdsmdg.cycle.gameworld.GameWorld;

public class Button {

    float width, height;
    Vector2 position = new Vector2();
    Rectangle rectangle;
    GameWorld myWorld;
    TextureRegion regionOn, regionOff, current;

    public Button(GameWorld world, float x, float y, float width, float height, TextureRegion regionOn, TextureRegion regionOff) {
        this.height = height;
        this.regionOn = regionOn;
        this.regionOff = regionOff;
        this.width = width;
        this.position.x = x;
        this.position.y = y;
        this.rectangle = new Rectangle(x, y, width, height);
        this.myWorld = world;

        current = regionOff;
    }

    public boolean isTouched(int x, int y) {
        return rectangle.contains(x, y);
    }

    public void onDraw(SpriteBatch batcher) {
        batcher.begin();
        batcher.draw(current, position.x, position.y, width, height);
        batcher.end();
    }

    public void onTouchDown() {
        current = regionOn;
    }

    public void onTouchUp() {
        current = regionOff;
        myWorld.setGameStateRunning();
    }

    public void onRemoveTouch() {
        current = regionOff;
    }

    public float getHeight() {
        return height;
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
}
