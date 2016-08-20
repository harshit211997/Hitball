package com.sdsmdg.cycle;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.sdsmdg.cycle.chelpers.AssetLoader;
import com.sdsmdg.cycle.screens.SplashScreen;

public class CGame extends Game{

    private static final String TAG = CGame.class.getSimpleName();
    public static PlayServices playServices;

    public CGame(PlayServices playServices) {
        this.playServices = playServices;
    }

    @Override
    public void create() {
        Gdx.app.log(TAG, "created");
        AssetLoader.load(Gdx.graphics.getWidth());
        setScreen(new SplashScreen(this));
    }

    @Override
    public void dispose() {
        AssetLoader.dispose();
        super.dispose();
    }
}
