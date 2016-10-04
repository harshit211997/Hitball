package com.sdsmdg.cycle.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.sdsmdg.cycle.gameworld.GameWorld;

public class Star {

    float x, y;
    float initialRadius;
    float radius;
    float rotation = 0;

    public Star() {
        y = (float) Math.random() * GameWorld.screenHeight / 2;
        x = (float) Math.random() * GameWorld.screenWidth;
        initialRadius = radius = 1 + (float) Math.random() * 2;
        rotation = (float)Math.random();
    }

    public void onDraw(SpriteBatch batcher, ShapeRenderer shapeRenderer) {
        batcher.end();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(1, 1, 1, 1);
        shapeRenderer.circle(x, y, radius);
        shapeRenderer.end();
        batcher.begin();
    }

    public void update(float delta) {
        rotation += delta;
        radius = initialRadius + initialRadius / 4 * (float) Math.sin(rotation * 4);
    }

}
