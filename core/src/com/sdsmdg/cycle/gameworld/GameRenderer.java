package com.sdsmdg.cycle.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.BufferUtils;
import com.sdsmdg.cycle.chelpers.AssetLoader;
import com.sdsmdg.cycle.objects.Background;
import com.sdsmdg.cycle.objects.Ball;
import com.sdsmdg.cycle.objects.Bat;

import java.nio.IntBuffer;

public class GameRenderer {

    private GameWorld myWorld;
    private OrthographicCamera cam;
    private ShapeRenderer shapeRenderer;
    private BitmapFont font40, font80;

    private SpriteBatch batcher;

    private Bat bat;
    private Ball ball;

    private int screenWidth, screenHeight;

    GlyphLayout glyphLayout;

    private Background background;

    public GameRenderer(GameWorld world, int screenWidth, int screenHeight) {
        myWorld = world;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;

        background = new Background();

        glyphLayout = new GlyphLayout();

        bat = world.getBat();
        ball = world.getBall(0);

        cam = new OrthographicCamera();
        cam.setToOrtho(true, screenWidth, screenHeight);

        batcher = new SpriteBatch();
        batcher.setProjectionMatrix(cam.combined);
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(cam.combined);

        initAssets();
        initGameObjects();

        //Check max size of image that can be used
        IntBuffer buf = BufferUtils.newIntBuffer(16);
        Gdx.gl.glGetIntegerv(Gdx.gl.GL_MAX_TEXTURE_SIZE, buf);
        int maxSize = buf.get();
        Gdx.app.log("GL", "Max openGL texture size : " + String.valueOf(maxSize));
    }

    private void initGameObjects() {
        //the parameter true ensures that text is displayed not flipped
        font40 = AssetLoader.font40;
        font80 = AssetLoader.font80;
    }


    private void initAssets() {
    }

    public void render(float runTime) {

        batcher.begin();

        background.onDraw(myWorld, batcher);

        if (myWorld.isRunning()) {

            //Draw score while running
            drawScore();

            //Draw bat
            bat.onDraw(batcher);

            //Draw ball
            ball.onDraw(batcher);

        }

        if (myWorld.isReady()) {

            float logoWidth = screenWidth / 2.5f;
            float logoHeight = screenWidth / 2.5f;
            //Draw the logo on the home screen
            batcher.draw(AssetLoader.hitballRegion,
                    (screenWidth - logoWidth) / 2, screenHeight * 0.10f,
                    logoWidth, logoHeight);

            myWorld.getPlayReady().onDraw(batcher);

            myWorld.getAchievementButton().onDraw(batcher);

            myWorld.getLeaderBoardButton().onDraw(batcher);

            myWorld.getInfoButton().onDraw(batcher);

        } else if (myWorld.isOver()) {

            myWorld.getBoard().onDraw(batcher);

            myWorld.getPlayButton().onDraw(batcher);

            myWorld.getAchievementButton().onDraw(batcher);

            myWorld.getLeaderBoardButton().onDraw(batcher);

        }

        batcher.end();

    }

    public void drawScore() {
        String text = String.valueOf(myWorld.getScore());
        glyphLayout.setText(font80, text);
        float w = glyphLayout.width;
        float h = glyphLayout.height;
        font80.draw(batcher, text, (screenWidth - w) / 2, (screenHeight / 4 - h / 2));
    }
}
