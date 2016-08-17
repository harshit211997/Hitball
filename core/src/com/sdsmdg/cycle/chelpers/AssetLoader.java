package com.sdsmdg.cycle.chelpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

public class AssetLoader {

    public static Sound sound, hit, gameOver;
    public static BitmapFont font40, font80, font120;
    public static Texture bat, ball, playOn, playOff, cloud, cloud1, sun, background, mdgLogo, fan;
    public static Sprite batRegion, ballRegion, playRegionOn, playRegionOff, cloudRegion, cloud1Region, sunRegion, backgroundRegion, mdgLogoRegion, fanRegion;

    public static void load(int screenWidth) {
        bat = new Texture(Gdx.files.internal("bat.png"));
        bat.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        batRegion = new Sprite(bat);
        ball = new Texture(Gdx.files.internal("ball.png"));
        ballRegion = new Sprite(ball);

        playOn = new Texture(Gdx.files.internal("play_press.png"));
        playRegionOn = new Sprite(playOn);

        playOff = new Texture(Gdx.files.internal("play.png"));
        playRegionOff = new Sprite(playOff);

        cloud = new Texture(Gdx.files.internal("cloud.png"));
        cloudRegion = new Sprite(cloud);
        cloudRegion.flip(false, true);

        sun = new Texture(Gdx.files.internal("sun.png"));
        sunRegion = new Sprite(sun);

        background = new Texture(Gdx.files.internal("background.png"));
        backgroundRegion = new Sprite(background);
        backgroundRegion.flip(false, true);

        mdgLogo = new Texture(Gdx.files.internal("mdg_logo.png"));
        mdgLogo.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        mdgLogoRegion = new Sprite(mdgLogo);
        mdgLogoRegion.flip(false, true);

        cloud = new Texture(Gdx.files.internal("cloud.png"));
        cloudRegion = new Sprite(cloud);
        cloudRegion.flip(false, true);

        cloud1 = new Texture(Gdx.files.internal("cloud1.png"));
        cloud1Region = new Sprite(cloud1);
        cloud1Region.flip(false, true);

        fan = new Texture(Gdx.files.internal("fan.png"));
        fanRegion = new Sprite(fan);

        createFont(screenWidth);

        loadSounds();

    }

    private static void createFont(int screenWidth) {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("font.otf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = (40 * screenWidth) / 480;//Scaling it according to the screenWidth
        parameter.flip = true;
        font40 = generator.generateFont(parameter);

        parameter.size = (80 * screenWidth) / 480;//Scaling it according to the screenWidth
        font80 = generator.generateFont(parameter);

        parameter.size = (120 * screenWidth) / 480;
        font120 = generator.generateFont(parameter);

        generator.dispose();
    }

    private static void loadSounds() {
        sound = Gdx.audio.newSound(Gdx.files.internal("hit.mp3"));
        hit = Gdx.audio.newSound(Gdx.files.internal("pop.mp3"));
        gameOver = Gdx.audio.newSound(Gdx.files.internal("sound.wav"));
    }

    public static void dispose() {
        // We must dispose of the texture when we are finished.
        bat.dispose();
        ball.dispose();
        playOn.dispose();
        playOff.dispose();
        sun.dispose();
        background.dispose();
        sound.dispose();
        hit.dispose();
    }
    
}