package com.sdsmdg.cycle.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.sdsmdg.cycle.gameworld.GameWorld;

public class Board {

    private int score;
    private int highscore;
    private Vector2 position;
    private float width, height;
    private GlyphLayout glyphLayout;
    private GameWorld myWorld;
    private int screenWidth, screenHeight;
    private BitmapFont font40, font120;

    public Board(GameWorld world, float width, float height, Vector2 position) {
        this.height = height;
        this.position = position;
        this.width = width;
        this.myWorld = world;

        screenWidth = myWorld.getScreenWidth();
        screenHeight = myWorld.getScreenHeight();

        glyphLayout = new GlyphLayout();

        generateFont();
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

    public void onDraw(SpriteBatch batch, ShapeRenderer renderer) {

        renderer.begin(ShapeRenderer.ShapeType.Filled);
        //renderer.setColor(34/255f, 126/255f, 200/255f, 1f);
        renderer.setColor(204/255f, 0/255f, 0, 1);
        renderer.rect(
                position.x - width / 2, position.y - height / 2,
                width, height
        );
        renderer.end();

        renderer.begin(ShapeRenderer.ShapeType.Line);
        renderer.rect(
                position.x - width / 2, position.y - height / 2,
                width, height
        );
        renderer.end();


        batch.begin();

        drawText(String.valueOf(myWorld.getScore()),
                batch,
                position.x, position.y - height / 5,
                font120);

        drawText("Best Score",
                batch,
                position.x, position.y + height / 2 - (60 * screenWidth) / 480,
                font40);

        drawText(String.valueOf(myWorld.getHighScore()),
                batch,
                position.x, position.y + height / 2 - (30 * screenWidth) / 480,
                font40);

        batch.end();
    }

    private void drawText(String text, SpriteBatch batch, float x, float y, BitmapFont font) {
        glyphLayout.setText(font, text);
        float w = glyphLayout.width;
        float h = glyphLayout.height;
        font.draw(batch, text, x - w / 2, y - h / 2);
    }

}
