package com.sdsmdg.cycle.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sdsmdg.cycle.gameworld.GameWorld;

public class StarController {

    Sprite sprite;
    float theta = 0;
    GameWorld mGameWorld;
    boolean visible = false;
    float timer = 0;
    float initialX;

    public StarController(GameWorld world, Sprite sprite) {
        this.sprite = sprite;
        this.mGameWorld = world;
        initialX = sprite.getX();
    }

    public void update(float delta) {
        if(visible) {
            float newX = mGameWorld.getScreenWidth() / 2 - (float) Math.cos(theta) * (mGameWorld.getScreenWidth() / 2 - initialX);
            sprite.setX(newX);

            if(System.currentTimeMillis() > timer + 3000) {
                timer = 0;
                visible = false;
            }

            theta += delta;
        }
    }

    public boolean isTaken(Ball ball) {

        if(visible) {
            float cToCDistance = (float)Math.sqrt(Math.pow(ball.getPosition().x - (sprite.getX() + sprite.getWidth() / 2), 2) + Math.pow(ball.getPosition().y - (sprite.getY() + sprite.getWidth() / 2), 2));
            if(cToCDistance < ball.getRadius() + sprite.getWidth() / 2) {
                visible = false;
                return true;
            }
        }

        return false;
    }

    public void deployStar() {
        visible = true;
        timer = System.currentTimeMillis();
        Gdx.app.log("StarController", "visible = true");
    }

    public void removeStar() {
        visible = false;
        timer = 0;
    }

    public void onDraw(SpriteBatch batcher) {
        if(visible) {
            sprite.draw(batcher);
        }
    }

}
