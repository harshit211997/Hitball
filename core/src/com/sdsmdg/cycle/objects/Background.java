package com.sdsmdg.cycle.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sdsmdg.cycle.chelpers.AssetLoader;
import com.sdsmdg.cycle.gameworld.GameWorld;

public class Background {

    BackgroundState state;

    public Background() {
        float rand = (float)Math.random();
        if(rand > 0.5) {
            state = BackgroundState.NIGHT;
        } else {
            state = BackgroundState.DAY;
        }
    }

    private enum BackgroundState {
        DAY, NIGHT
    }

    public void onDraw(GameWorld world, SpriteBatch batcher) {

        // Fill the entire screen with blue, to prevent potential flickering.
        if(state == BackgroundState.DAY) {
            Gdx.gl.glClearColor(51 / 255f, 204 / 255f, 1f, 1f);//Blue
        } else if(state == BackgroundState.NIGHT) {
            Gdx.gl.glClearColor(0f, 0f, 0f, 1f);//Black
        }
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //draw background
        float backgroundWidth = world.getScreenWidth();
        float backgroundHeight = backgroundWidth * 1081 / 1242;
        batcher.draw(AssetLoader.backgroundRegion, 0, world.getScreenHeight() - backgroundHeight,
                backgroundWidth, backgroundHeight);

        //Draw sun
        if(state == BackgroundState.DAY)
            world.getSun().onDraw(batcher);
        else
            world.getMoon().onDraw(batcher);

        //Draw clouds
        for(int i=0;i<world.getClouds().size();i++) {
            world.getClouds().get(i).onDraw(batcher);
        }

        //Draw the moving part of windmill
        world.getFan().onDraw(batcher);
    }

}
