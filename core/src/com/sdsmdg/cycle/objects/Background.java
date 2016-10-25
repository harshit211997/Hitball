package com.sdsmdg.cycle.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.sdsmdg.cycle.chelpers.AssetLoader;
import com.sdsmdg.cycle.gameworld.GameWorld;

import java.util.ArrayList;
import java.util.List;

public class Background {

    BackgroundState state;
    private List<Cloud> clouds = new ArrayList<Cloud>();
    private Fan fan;
    private Sun sun;
    private Moon moon;
    private List<NightStar> nightStars = new ArrayList<NightStar>();
    private final int NO_STARS = 50;

    public Background() {
        float rand = (float)Math.random();
        if(rand > 0.5) {
            state = BackgroundState.NIGHT;
        } else {
            state = BackgroundState.DAY;
        }
        float screenWidth = GameWorld.screenWidth;
        float screenHeight = GameWorld.screenHeight;

        float cloudWidth = screenWidth / 3;
        float cloudHeight = (cloudWidth * 121) / 232;
        clouds.add(new Cloud(cloudWidth, cloudHeight,
                new Vector2(screenWidth / 10, screenHeight / 20),//cloud's positions
                new Vector2(screenWidth / 2000f, 0),//cloud's velocity
                AssetLoader.cloudRegion));

        cloudWidth /= 2;
        cloudHeight /= 2;
        clouds.add(new Cloud(cloudWidth, cloudHeight,
                new Vector2(screenWidth - cloudWidth, cloudHeight / 2),
                new Vector2(screenWidth / 1500f, 0),
                AssetLoader.cloud1Region));

        fan = new Fan(screenWidth / 10, screenWidth / 10,
                new Vector2(screenWidth / 6.8f, screenHeight - screenWidth * 0.86f),
                AssetLoader.fanRegion);

        sun = new Sun(new Vector2(screenWidth * 0.80f, screenHeight / 3),
                AssetLoader.sunRegion);

        moon = new Moon(new Vector2(screenWidth * 0.75f, screenHeight / 3),
                AssetLoader.moonRegion);

        for (int i = 1; i < NO_STARS; i++) {
            nightStars.add(new NightStar());
        }

    }

    public void update(float delta) {
        moon.update(delta);
        sun.update(delta);

        for (int i = 0; i < clouds.size(); i++) {
            clouds.get(i).update(delta);
        }

        for (int i = 0; i < nightStars.size(); i++) {
            nightStars.get(i).update(delta);
        }

        fan.update(delta);
    }

    private enum BackgroundState {
        DAY, NIGHT
    }

    public void onDraw(GameWorld world, ShapeRenderer shapeRenderer, SpriteBatch batcher) {

        // Fill the entire screen with blue, to prevent potential flickering.
        if(state == BackgroundState.DAY) {
            Gdx.gl.glClearColor(51 / 255f, 204 / 255f, 1f, 1f);//Blue
        } else if(state == BackgroundState.NIGHT) {
            Gdx.gl.glClearColor(0f, 0f, 0f, 1f);//Black
        }
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if(state == BackgroundState.NIGHT) {
            for (int i = 0; i < nightStars.size(); i++) {
                nightStars.get(i).onDraw(batcher, shapeRenderer);
            }
        }

        //draw background
        float backgroundWidth = world.getScreenWidth();
        float backgroundHeight = backgroundWidth * 1081 / 1242;
        batcher.draw(AssetLoader.backgroundRegion, 0, world.getScreenHeight() - backgroundHeight,
                backgroundWidth, backgroundHeight);

        //Draw sun
        if(state == BackgroundState.DAY)
            sun.onDraw(batcher);
        else {
            moon.onDraw(batcher);
        }

        //Draw clouds
        if(state == BackgroundState.DAY ) {
            for (int i = 0; i < clouds.size(); i++) {
                clouds.get(i).onDraw(batcher);
            }
        }

        //Draw the moving part of windmill
        fan.onDraw(batcher);
    }

}
