package com.sdsmdg.cycle.chelpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

public class AssetLoader {

    public static Sound hit, buttonClick;
    public static BitmapFont font20, font40, font80, font120;
    public static Texture bat, ball, playOn, playOff, cloud, cloud1, sun, background, mdgLogo, fan, achievement, achievementPressed, leaderboard, leaderboardPressed, scorecard, moonTexture, hitballTexture, aboutUsTexture, aboutUsSmallTexture, volumeOnTexture, volumeOffTexture, starTexture;
    public static Sprite batRegion, ballRegion, playRegionOn, playRegionOff, cloudRegion, cloud1Region, sunRegion, backgroundRegion, mdgLogoRegion, fanRegion, achievementRegion, achievementPressedRegion, leaderboardRegion, scorecardRegion, leaderboardPressedRegion, moonRegion, hitballRegion, aboutUsRegion, aboutUsSmallRegion, volumeOnRegion, volumeOffRegion, starRegion;

    public static void load(int screenWidth) {

        bat = new Texture(Gdx.files.internal("bat.png"));
        bat.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        batRegion = new Sprite(bat);

        ball = new Texture(Gdx.files.internal("ball.png"));
        ballRegion = new Sprite(ball);

        playOn = new Texture(Gdx.files.internal("play_press.png"));
        playRegionOn = new Sprite(playOn);
        playRegionOn.flip(false, true);

        playOff = new Texture(Gdx.files.internal("play.png"));
        playRegionOff = new Sprite(playOff);
        playRegionOff.flip(false, true);

        sun = new Texture(Gdx.files.internal("sun.png"));
        sunRegion = new Sprite(sun);

        background = new Texture(Gdx.files.internal("background.png"));
        backgroundRegion = new Sprite(background);
        backgroundRegion.flip(false, true);

        mdgLogo = new Texture(Gdx.files.internal("mdg_logo.png"));
        mdgLogo.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        mdgLogoRegion = new Sprite(mdgLogo);
        mdgLogoRegion.flip(false, true);

        hitballTexture = new Texture(Gdx.files.internal("hitball_logo_unlined.png"));
        hitballTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        hitballRegion = new Sprite(hitballTexture);
        hitballRegion.flip(false, true);

        cloud = new Texture(Gdx.files.internal("cloud.png"));
        cloudRegion = new Sprite(cloud);
        cloudRegion.flip(false, true);

        cloud1 = new Texture(Gdx.files.internal("cloud1.png"));
        cloud1Region = new Sprite(cloud1);
        cloud1Region.flip(false, true);

        fan = new Texture(Gdx.files.internal("fan.png"));
        fanRegion = new Sprite(fan);

        achievement = new Texture(Gdx.files.internal("achievement.png"));
        achievementRegion = new Sprite(achievement);
        achievementRegion.flip(false, true);

        leaderboard = new Texture(Gdx.files.internal("leaderboard.png"));
        leaderboardRegion = new Sprite(leaderboard);
        leaderboardRegion.flip(false, true);

        scorecard = new Texture(Gdx.files.internal("scorecard.png"));
        scorecardRegion = new Sprite(scorecard);
        scorecardRegion.flip(false, true);

        leaderboardPressed = new Texture(Gdx.files.internal("leaderboard_pressed.png"));
        leaderboardPressedRegion = new Sprite(leaderboardPressed);
        leaderboardPressedRegion.flip(false, true);

        achievementPressed = new Texture(Gdx.files.internal("achievement_pressed.png"));
        achievementPressedRegion = new Sprite(achievementPressed);
        achievementPressedRegion.flip(false, true);

        moonTexture = new Texture(Gdx.files.internal("moon.png"));
        moonRegion = new Sprite(moonTexture);

        aboutUsTexture = new Texture(Gdx.files.internal("about_us.png"));
        aboutUsRegion = new Sprite(aboutUsTexture);
        aboutUsRegion.flip(false, true);

        aboutUsSmallTexture = new Texture(Gdx.files.internal("about_us_small.png"));
        aboutUsSmallRegion = new Sprite(aboutUsSmallTexture);
        aboutUsSmallRegion.flip(false, true);

        volumeOnTexture = new Texture(Gdx.files.internal("speaker_on.png"));
        volumeOnRegion = new Sprite(volumeOnTexture);

        volumeOffTexture = new Texture(Gdx.files.internal("speaker_off.png"));
        volumeOffRegion = new Sprite(volumeOffTexture);

        starTexture = new Texture(Gdx.files.internal("star.png"));
        starRegion = new Sprite(starTexture);
        starRegion.flip(false, true);

        createFont(screenWidth);

        loadSounds();

    }

    private static void createFont(int screenWidth) {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("font.otf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = (20 * screenWidth) / 480;//Scaling it according to the screenWidth
        parameter.flip = true;
        font20 = generator.generateFont(parameter);

        parameter.size = (40 * screenWidth) / 480;
        font20 = generator.generateFont(parameter);

        parameter.size = (80 * screenWidth) / 480;//Scaling it according to the screenWidth
        font80 = generator.generateFont(parameter);

        parameter.size = (120 * screenWidth) / 480;
        font120 = generator.generateFont(parameter);

        generator.dispose();
    }

    private static void loadSounds() {
        hit = Gdx.audio.newSound(Gdx.files.internal("pop.mp3"));
        buttonClick = Gdx.audio.newSound(Gdx.files.internal("button_click.wav"));
    }

    public static void dispose() {
        // We must dispose of the texture when we are finished.
        bat.dispose();
        ball.dispose();
        playOn.dispose();
        playOff.dispose();
        cloud.dispose();
        cloud1.dispose();
        background.dispose();
        mdgLogo.dispose();
        fan.dispose();
        sun.dispose();
        achievement.dispose();
        achievementPressed.dispose();
        leaderboard.dispose();
        leaderboardPressed.dispose();
        scorecard.dispose();
        moonTexture.dispose();
        hitballTexture.dispose();
        aboutUsSmallTexture.dispose();
        aboutUsTexture.dispose();
        volumeOnTexture.dispose();
        volumeOffTexture.dispose();
        starTexture.dispose();

        //dispose the audio files
        disposeAudio();

        //dispose fonts
        font40.dispose();
        font80.dispose();
        font120.dispose();
    }

    public static void disposeAudio() {
        hit.dispose();
        buttonClick.dispose();
    }
    
}