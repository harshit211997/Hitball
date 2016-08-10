package com.sdsmdg.cycle.chelpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AssetLoader {

    public static Texture bat, ball, play, replay, cloud, grassLeft, grassRight, sun, parachute;
    public static TextureRegion batRegion, ballRegion, playRegion, replayRegion, cloudRegion, grassLeftRegion, grassRightRegion, sunRegion, parachuteRegion;


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

        grassLeft = new Texture(Gdx.files.internal("grass_left.png"));
        grassLeftRegion = new TextureRegion(grassLeft);
        grassLeftRegion.flip(false, true);

        grassRight = new Texture(Gdx.files.internal("grass_right.png"));
        grassRightRegion = new TextureRegion(grassRight);
        grassRightRegion.flip(false, true);

        parachute = new Texture(Gdx.files.internal("parachute.png"));
        parachuteRegion = new TextureRegion(parachute);

        sun = new Texture(Gdx.files.internal("sun.png"));
        sunRegion = new TextureRegion(sun);

    }

    public static void dispose() {
        // We must dispose of the texture when we are finished.
        bat.dispose();
        ball.dispose();
        play.dispose();
        replay.dispose();
    }

}