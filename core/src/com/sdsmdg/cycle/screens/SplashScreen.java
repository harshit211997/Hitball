package com.sdsmdg.cycle.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sdsmdg.cycle.CGame;
import com.sdsmdg.cycle.TweenAccessors.SpriteAccessor;
import com.sdsmdg.cycle.chelpers.AssetLoader;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenEquations;
import aurelienribon.tweenengine.TweenManager;

public class SplashScreen implements Screen {

    private final String TAG = SplashScreen.class.getSimpleName();
    private OrthographicCamera camera;
    private CGame game;
    private SpriteBatch batcher;
    private float screenWidth, screenHeight;
    private TweenManager manager;
    private Sprite sprite;

    public SplashScreen(CGame game, AssetLoader loader) {

        this.screenWidth = Gdx.graphics.getWidth();
        this.screenHeight = Gdx.graphics.getHeight();

        camera = new OrthographicCamera();
        camera.setToOrtho(true, screenWidth, screenHeight);

        batcher = new SpriteBatch();
        batcher.setProjectionMatrix(camera.combined);

        this.game = game;

        if (!game.playServices.isSignedIn())
            game.playServices.signIn();

        float logoWidth = screenWidth / 4;
        float logoHeight = 146 * logoWidth / 94;

        sprite = loader.mdgLogoRegion;
        sprite.setColor(1, 1, 1, 0f);
        sprite.setPosition((screenWidth - logoWidth) / 2, (screenHeight - logoHeight) / 2);
        sprite.setSize(logoWidth, logoHeight);

        setupTween();

    }

    private void setupTween() {
        Tween.registerAccessor(Sprite.class, new SpriteAccessor());
        manager = new TweenManager();

        TweenCallback cb = new TweenCallback() {
            @Override
            public void onEvent(int type, BaseTween<?> source) {
                game.setScreen(new GameScreen(game));
            }
        };

        Tween.to(sprite, SpriteAccessor.ALPHA, 1.5f).target(1)
                .ease(TweenEquations.easeInOutQuad).repeatYoyo(1, 0.75f)
                .setCallback(cb).setCallbackTriggers(TweenCallback.COMPLETE)
                .start(manager);
    }

    @Override
    public void dispose() {
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        manager.update(delta);
        batcher.begin();
        sprite.draw(batcher);
        batcher.end();
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
