package com.sdsmdg.cycle.objects;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.sdsmdg.cycle.gameworld.GameWorld;

public class Button {

    TextureRegion region;
    int width, height;
    Vector2 position = new Vector2();
    Rectangle rectangle;
    GameWorld myWorld;

    public Button(GameWorld world, int height, TextureRegion region, int width, int x, int y) {
        this.height = height;
        this.region = region;
        this.width = width;
        this.position.x = x;
        this.position.y = y;
        this.rectangle = new Rectangle(x - width / 2, y - height / 2, x + width / 2, y + height / 2);
        this.myWorld = world;
    }

    public void onClick() {
        myWorld.setGameStateRunning();
    }

    public int getHeight() {
        return height;
    }

    public TextureRegion getRegion() {
        return region;
    }

    public int getWidth() {
        return width;
    }

    public Vector2 getPosition() {
        return position;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }
}
