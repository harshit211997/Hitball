package com.sdsmdg.cycle.chelpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AssetLoader {

    public static Texture bat, ball, play, replay, cloud, sun, background;
    public static TextureRegion batRegion, ballRegion, playRegion, replayRegion, cloudRegion, sunRegion, backgroundRegion;


    public static void load() {
        bat = new Texture(Gdx.files.internal("bat.png"));
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