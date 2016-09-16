package com.sdsmdg.cycle.objects;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.sdsmdg.cycle.gameworld.GameWorld;

public class Fan {

    private GameWorld world;
    private Vector2 position;
    private int rotation = 0;
    private Sprite sprite;
    private float width, height;
    private int w;

    public Fan(GameWorld world, float width, float height, Vector2 position, Sprite sprite) {
        this.position = position;
        this.sprite = sprite;
        this.world = world;
        this.width = width;
        this.height = height;

        w = 96;
    }

    public void update(float delta) {
        rotation += w * delta;
    }

    public void onDraw(SpriteBatch batcher) {
        batcher.begin();

        batcher.draw(sprite,
                position.x, position.y,
                width / 2, height / 2,
                width, height,
                1, 1,
                rotation
                );

        batcher.end();

    }
}
