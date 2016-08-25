package com.sdsmdg.cycle.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.TimeUtils;
import com.sdsmdg.cycle.CGame;
import com.sdsmdg.cycle.chelpers.AssetLoader;

public class SplashScreen implements Screen {

    private final String TAG = SplashScreen.class.getSimpleName();
    private OrthographicCamera camera;
    private CGame game;
    private long startTime;
    private final long DURATION = 2000;
    private SpriteBatch batcher;
    private float screenWidth, screenHeight;

    public SplashScreen(CGame game) {

        this.screenWidth = Gdx.graphics.getWidth();
        this.screenHeight = Gdx.graphics.getHeight();

        camera = new OrthographicCamera();
        camera.setToOrtho(true, screenWidth, screenHeight);

        batcher = new SpriteBatch();
        batcher.setProjectionMatrix(camera.combined);

        this.game = game;

        if (!game.playServices.isSignedIn())
            game.playServices.signIn();

    }

    @Override
    public void dispose() {

    }

    @Override
    public void show() {
        startTime = TimeUtils.millis();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batcher.begin();

        float logoWidth = screenWidth / 4;
        float logoHeight = 146 * logoWidth / 94;
        batcher.draw(AssetLoader.mdgLogoRegion,
                (screenWidth - logoWidth) / 2, (screenHeight - logoHeight) / 2,
                0, 0,
                logoWidth, logoHeight,
                1, 1,
                0);

        batcher.end();

        if (TimeUtils.millis() >= startTime + DURATION) {
            game.setScreen(new GameScreen(game));
        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }
}
