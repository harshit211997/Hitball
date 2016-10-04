package com.sdsmdg.cycle.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.sdsmdg.cycle.TweenAccessors.VectorAccessor;
import com.sdsmdg.cycle.gameworld.GameWorld;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenEquations;
import aurelienribon.tweenengine.TweenManager;

public class Board {

    private int score;
    private int highscore;
    private Vector2 finalPosition;
    private Vector2 position;
    private float width, height;
    private GlyphLayout glyphLayout;
    private GameWorld myWorld;
    private int screenWidth, screenHeight;
    private BitmapFont font40, font120;
    private TweenManager manager;
    private JumpingTextView textView;
    private boolean newBestScore = false;

    public Board(GameWorld world, float width, float height, Vector2 position) {
        this.height = height;
        this.finalPosition = position;
        this.width = width;
        this.myWorld = world;

        this.position = new Vector2(finalPosition);

        screenWidth = myWorld.getScreenWidth();
        screenHeight = myWorld.getScreenHeight();

        glyphLayout = new GlyphLayout();

        manager = new TweenManager();

        textView = new JumpingTextView("New Best Score!");

        generateFont();
    }

    public void onGameRunning() {
        newBestScore = false;
    }

    public void onGameOver(boolean isNewBestScore) {
        setUpTween();
        this.newBestScore = isNewBestScore;
    }

    private void setUpTween() {
        Tween.registerAccessor(Vector2.class, new VectorAccessor());
        position.x = 0;

        Tween.to(position, VectorAccessor.X, 0.3f).target(finalPosition.x)
                .ease(TweenEquations.easeInOutExpo)
                .start(manager);
    }

    public void generateFont() {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("font.otf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = (40 * screenWidth) / 480;//Scaling it according to the screenWidth
        parameter.flip = true;
        font40 = generator.generateFont(parameter);

        parameter.size = (120 * screenWidth) / 480;
        font120 = generator.generateFont(parameter);

        generator.dispose();
    }

    public void update(float delta) {
        manager.update(delta);
        textView.update(delta);
    }

    public void onDraw(SpriteBatch batcher, ShapeRenderer shapeRenderer) {

        drawBackground(batcher, shapeRenderer);

        drawText(String.valueOf(myWorld.getScore()),
                batcher,
                position.x, position.y - height / 5,
                font120);

        if (newBestScore) {
            textView.render(batcher,
                    screenWidth - position.x,
                    position.y + height / 2 - (60 * screenWidth) / 480
            );
        } else {
            drawText("Best Score",
                    batcher,
                    screenWidth - position.x,
                    position.y + height / 2 - (60 * screenWidth) / 480,
                    font40
            );

            drawText(String.valueOf(myWorld.getHighScore()),
                    batcher,
                    screenWidth - position.x, position.y + height / 2 - (30 * screenWidth) / 480,
                    font40
            );

        }

    }

    //draws translucent background
    private void drawBackground(SpriteBatch batcher, ShapeRenderer shapeRenderer) {
        float boardWidth = screenWidth;
        float boardHeight = 0.5f * screenWidth;

        batcher.end();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        Gdx.graphics.getGL20().glEnable(GL20.GL_BLEND);//This line is added so that the alpha of the board background might work
        shapeRenderer.setColor(0, 0, 0, 0.5f);
        shapeRenderer.rect(screenWidth / 2 - boardWidth / 2, screenHeight / 2 - boardHeight / 2,
                boardWidth, boardHeight);
        shapeRenderer.end();
        batcher.begin();
    }

    private void drawText(String text, SpriteBatch batch, float x, float y, BitmapFont font) {
        glyphLayout.setText(font, text);
        float w = glyphLayout.width;
        float h = glyphLayout.height;
        font.draw(batch, text, x - w / 2, y - h / 2);
    }

}
