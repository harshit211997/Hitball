package com.sdsmdg.cycle.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.sdsmdg.cycle.chelpers.AssetLoader;
import com.sdsmdg.cycle.objects.Ball;
import com.sdsmdg.cycle.objects.Bat;
import com.sdsmdg.cycle.objects.Button;

public class GameRenderer {

    private GameWorld myWorld;
    private OrthographicCamera cam;
    private ShapeRenderer shapeRenderer;
    private BitmapFont font;

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
        //the parameter true ensures that text is displayed not flipped
        font = new BitmapFont(true);
    }


    private void initAssets() {
    }

    public void render(float runTime) {

        // Fill the entire screen with black, to prevent potential flickering.
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batcher.begin();

        if(myWorld.isRunning()) {

            //Draw grass
            batcher.draw(AssetLoader.grassLeftRegion, 0, screenHeight - screenWidth / 3,
                    0, 0,
                    2 * screenWidth / 3, screenWidth / 3,
                    1, 1,
                    0);

            batcher.draw(AssetLoader.grassRightRegion, screenWidth / 3, screenHeight - screenWidth / 3,
                    0, 0,
                    2 * screenWidth / 3, screenWidth / 3,
                    1, 1,
                    0);

            //Draw sun
            TextureRegion sunRegion = AssetLoader.sunRegion;
            batcher.draw(sunRegion, screenWidth * 2 / 3f, screenHeight / 4,
                    0, 0,
                    screenWidth / 4, screenWidth / 4,
                    1, 1,
                    0);

            //Draw clouds
            batcher.draw(AssetLoader.cloudRegion, screenWidth / 3, screenHeight / 5,
                    0, 0,
                    screenWidth / 6, screenWidth / 8,
                    1, 1,
                    0);

            batcher.draw(AssetLoader.cloudRegion, screenWidth * 3 / 4, screenHeight / 2,
                    0, 0,
                    screenWidth / 6, screenWidth / 8,
                    1, 1,
                    0);

            //Draw score while running
            font.draw(batcher, String.valueOf(myWorld.getScore()), 10, 10);

            //Draw bat
            batcher.draw(AssetLoader.batRegion, bat.getPosition().x, bat.getPosition().y,
                    bat.getOriginX() - bat.getPosition().x, bat.getOriginY() - bat.getPosition().y,
                    bat.getWidth(), bat.getHeight(),
                    1, 1,
                    bat.getRotation());

            //Draw ball
            batcher.draw(AssetLoader.ballRegion, ball1.getPosition().x - ball1.getRadius(), ball1.getPosition().y - ball1.getRadius(),
                    ball1.getRadius(), ball1.getRadius(),
                    ball1.getRadius() * 2, ball1.getRadius() * 2,
                    1, 1,
                    ball1.getRotation());

        }else if(myWorld.isReady()) {
            Button playButton = myWorld.getPlayButton();
            batcher.draw(playButton.getRegion(), playButton.getPosition().x - playButton.getWidth() / 2 , playButton.getPosition().y - playButton.getHeight() / 2);
        }else if(myWorld.isOver()) {

            font.draw(batcher, String.valueOf(myWorld.getScore()), 10, 10);

            Button replayButton = myWorld.getReplayButton();
            batcher.draw(replayButton.getRegion(), replayButton.getPosition().x - replayButton.getWidth() / 2, replayButton.getPosition().y - replayButton.getHeight() / 2);
        }

        batcher.end();

    }
}
