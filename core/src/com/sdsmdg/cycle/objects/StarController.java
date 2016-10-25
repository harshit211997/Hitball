package com.sdsmdg.cycle.objects;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sdsmdg.cycle.gameworld.GameWorld;

public class StarController {

    Sprite sprite;
    float theta = 0;
    GameWorld mGameWorld;
    boolean visible = false;
    float timer = 0;

    public StarController(GameWorld world, Sprite sprite) {
        this.sprite = sprite;
        this.mGameWorld = world;
    }

    public void update(float delta) {
        if(visible) {
            float oldX = sprite.getX();
            float newX = mGameWorld.getScreenWidth() / 2 + (float) Math.cos(theta) * (mGameWorld.getScreenWidth() / 2 - oldX);
            sprite.setX(newX);

            if(timer + 5000 >= System.currentTimeMillis()) {
                timer = 0;
                visible = false;
            }
        }
    }

    public boolean isTaken(Ball ball) {

        if(visible) {
            float cToCDistance = (float)Math.sqrt(Math.pow(ball.getPosition().x - sprite.getX(), 2) + Math.pow(ball.getPosition().y - sprite.getY(), 2));
            if(cToCDistance < ball.getRadius() + sprite.getRegionWidth()) {
                visible = false;
                return true;
            }
        }

        return false;
    }

    public void deployStar() {
        visible = true;
        timer = System.currentTimeMillis();
    }

    public void onDraw(SpriteBatch batcher) {
        if(visible) {
            sprite.draw(batcher);
        }
    }

}
