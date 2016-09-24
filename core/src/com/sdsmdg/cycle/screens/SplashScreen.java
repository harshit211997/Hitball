package com.sdsmdg.cycle.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sdsmdg.cycle.CGame;
import com.sdsmdg.cycle.chelpers.AssetLoader;

import aurelienribon.tweenengine.TweenManager;

public class SplashScreen implements Screen {

    private final String TAG = SplashScreen.class.getSimpleName();
    private OrthographicCamera camera;
    private CGame game;
    private SpriteBatch batcher;
    private float screenWidth, screenHeight;
    private Sprite mdgLogo, hitballLogo;
    private long time;
    private TweenManager manager;
    private Color color;

    public SplashScreen(CGame game) {

        color = new Color(1, 1, 1, 0);


        this.screenWidth = Gdx.graphics.getWidth();
        this.screenHeight = Gdx.graphics.getHeight();

        manager = new TweenManager();

        camera = new OrthographicCamera();
        camera.setToOrtho(true, screenWidth, screenHeight);

        batcher = new SpriteBatch();
        batcher.setProjectionMatrix(camera.combined);

        this.game = game;

        if (!game.playServices.isSignedIn())
            game.playServices.signIn();

        float logoWidth = screenWidth / 2;
        float logoHeight = logoWidth;

        hitballLogo = AssetLoader.hitballRegion;
        hitballLogo.setPosition((screenWidth - logoWidth) / 2, screenHeight / 2 - logoHeight);
        hitballLogo.setSize(logoWidth, logoHeight);

        logoWidth = screenWidth / 2f;
        logoHeight = 147 * logoWidth / 405;

        mdgLogo = AssetLoader.mdgLogoRegion;
        mdgLogo.setPosition((screenWidth - logoWidth) / 2, screenHeight - 1.5f * logoHeight);
        mdgLogo.setSize(logoWidth, logoHeight);

    }

    @Override
    public void dispose() {
    }

    @Override
    public void show() {
        time = System.currentTimeMillis();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(color.r, color.g, color.b, color.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        color.lerp(new Color(1, 1, 1, 0), 1f);
        batcher.begin();
        mdgLogo.draw(batcher);
        hitballLogo.draw(batcher);
        batcher.end();
        if(System.currentTimeMillis() >= time + 2000) {
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
