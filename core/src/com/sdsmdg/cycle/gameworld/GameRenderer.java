package com.sdsmdg.cycle.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.sdsmdg.cycle.chelpers.AssetLoader;
import com.sdsmdg.cycle.objects.Ball;
import com.sdsmdg.cycle.objects.Bat;
import com.sdsmdg.cycle.objects.Button;

public class GameRenderer {

    private GameWorld myWorld;
    private OrthographicCamera cam;
    private ShapeRenderer shapeRenderer;

    private SpriteBatch batcher;

    private Bat bat;
    private Ball ball1;

    private int screenWidth, screenHeight;

    public static Texture texture;

    public GameRenderer(GameWorld world, int screenWidth, int screenHeight) {
        myWorld = world;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;

        bat = world.getBat();
        ball1 = world.getBall(0);

        cam = new OrthographicCamera();
        cam.setToOrtho(true, screenWidth, screenHeight);

        batcher = new SpriteBatch();
        batcher.setProjectionMatrix(cam.combined);
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(cam.combined);

        initAssets();
        initGameObjects();
    }

    private void initGameObjects() {

    }


    private void initAssets() {

    }

    public void render(float runTime) {

        // Fill the entire screen with black, to prevent potential flickering.
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batcher.begin();

        batcher.draw(AssetLoader.backgroundRegion, 0, 0, screenWidth, screenHeight);

        if(myWorld.isRunning()) {
            batcher.draw(AssetLoader.batRegion, bat.getPosition().x, bat.getPosition().y,
                    bat.getOriginX() - bat.getPosition().x, bat.getOriginY() - bat.getPosition().y,
                    bat.getWidth(), bat.getHeight(),
                    1, 1,
                    bat.getRotation());

            batcher.draw(AssetLoader.ballRegion, ball1.getPosition().x - ball1.getRadius(), ball1.getPosition().y - ball1.getRadius(),
                    0, 0,
                    ball1.getRadius() * 2, ball1.getRadius() * 2,
                    1, 1,
                    0);
        }else if(myWorld.isReady()) {
            Button playButton = myWorld.getPlayButton();
            batcher.draw(playButton.getRegion(), playButton.getPosition().x - playButton.getWidth() / 2 , playButton.getPosition().y - playButton.getHeight() / 2);
        }

        batcher.end();

    }
}
