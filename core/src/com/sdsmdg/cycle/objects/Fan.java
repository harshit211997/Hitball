package com.sdsmdg.cycle.objects;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Fan {

    private Vector2 position;
    private int rotation = 0;
    private Sprite sprite;
    private float width, height;
    //w is omega, i.e. angular velocity of rotation of fan
    private int w;

    public Fan(float width, float height, Vector2 position, Sprite sprite) {
        this.position = position;
        this.sprite = sprite;
        this.width = width;
        this.height = height;

        w = 96;
    }

    public void update(float delta) {
        rotation += w * delta;
    }

    public void onDraw(SpriteBatch batcher) {

        batcher.draw(sprite,
                position.x, position.y,
                width / 2, height / 2,
                width, height,
                1, 1,
                rotation
                );

    }
}
