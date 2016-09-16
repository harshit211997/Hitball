package com.sdsmdg.cycle.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.sdsmdg.cycle.CGame;
import com.sdsmdg.cycle.chelpers.InputHandler;
import com.sdsmdg.cycle.gameworld.GameRenderer;
import com.sdsmdg.cycle.gameworld.GameWorld;

public class GameScreen implements Screen{

    private final String TAG = GameScreen.class.getSimpleName();
    private GameWorld world;
    private GameRenderer renderer;
    private float runTime = 0;

    public GameScreen(CGame game) {
        Gdx.app.log(TAG, "Attached");
        float screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();

        world = new GameWorld(game, (int)screenWidth, (int)screenHeight); // initialize world
        renderer = new GameRenderer(world, (int)screenWidth, (int)screenHeight, game.loader); // initialize renderer

        Gdx.input.setInputProcessor(new InputHandler(world));
    }

    @Override
    public void render(float delta) {
        runTime += delta;
        world.update(delta);
        renderer.render(runTime);
    }

    @Override
    public void resize(int width, int height) {
        Gdx.app.log(TAG, "resizing");
    }

    @Override
    public void show() {
        Gdx.app.log(TAG, "show called");
    }

    @Override
    public void hide() {
        Gdx.app.log(TAG, "hide called");
    }

    @Override
    public void pause() {
        Gdx.app.log(TAG, "pause called");
    }

    @Override
    public void resume() {
        Gdx.app.log(TAG, "resume called");
    }

    @Override
    public void dispose() {
        // Leave blank
    }
}
