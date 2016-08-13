package com.sdsmdg.cycle.chelpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

public class AssetLoader {

    public static BitmapFont font40, font80;
    public static Texture bat, ball, play, replay, cloud, sun, background, mdgLogo;
    public static TextureRegion batRegion, ballRegion, playRegion, replayRegion, cloudRegion, sunRegion, backgroundRegion, mdgLogoRegion;

    public static void load() {
        bat = new Texture(Gdx.files.internal("bat.png"));
        bat.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        batRegion = new TextureRegion(bat);
        ball = new Texture(Gdx.files.internal("ball.png"));
        ballRegion = new TextureRegion(ball);
        play = new Texture(Gdx.files.internal("play.png"));
        playRegion = new TextureRegion(play);
        replay = new Texture(Gdx.files.internal("replay.png"));
        replayRegion = new TextureRegion(replay);

        cloud = new Texture(Gdx.files.internal("cloud.png"));
        cloudRegion = new TextureRegion(cloud);
        cloudRegion.flip(false, true);

        sun = new Texture(Gdx.files.internal("sun.png"));
        sunRegion = new TextureRegion(sun);

        background = new Texture(Gdx.files.internal("background.png"));
        backgroundRegion = new TextureRegion(background);
        backgroundRegion.flip(false, true);

        mdgLogo = new Texture(Gdx.files.internal("mdg_logo.png"));
        mdgLogo.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        mdgLogoRegion = new TextureRegion(mdgLogo);
        mdgLogoRegion.flip(false, true);

        createFont();

    }

    private static void createFont() {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("font.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 40;
        parameter.flip = true;
        font40 = generator.generateFont(parameter);

        parameter.size = 80;
        font80 = generator.generateFont(parameter);

        generator.dispose();
    }

    public static void dispose() {
        // We must dispose of the texture when we are finished.
        bat.dispose();
        ball.dispose();
        play.dispose();
        replay.dispose();
        sun.dispose();
        background.dispose();
    }

}