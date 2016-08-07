package com.sdsmdg.cycle.chelpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AssetLoader {

    public static Texture bat;
    public static TextureRegion batRegion, ballRegion, playRegion, backgroundRegion, replayRegion;
    public static Texture ball;
    public static Texture play;
    public static Texture background;
    public static Texture replay;

    public static void load() {
        bat = new Texture(Gdx.files.internal("bat.png"));
        batRegion = new TextureRegion(bat);
        batRegion.flip(false, true);
        ball = new Texture(Gdx.files.internal("ball.png"));
        ballRegion = new TextureRegion(ball);
        play = new Texture(Gdx.files.internal("play.png"));
        playRegion = new TextureRegion(play);
        background = new Texture(Gdx.files.internal("stadium.jpg"));
        backgroundRegion = new TextureRegion(background);
        backgroundRegion.flip(false, true);
        replay = new Texture(Gdx.files.internal("replay.png"));
        replayRegion = new TextureRegion(replay);
    }

    public static void dispose() {
        // We must dispose of the texture when we are finished.
        bat.dispose();
        ball.dispose();
        play.dispose();
        replay.dispose();
    }

}