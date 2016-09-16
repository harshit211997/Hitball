package com.sdsmdg.cycle;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.sdsmdg.cycle.chelpers.AssetLoader;
import com.sdsmdg.cycle.screens.SplashScreen;

public class CGame extends Game{

    private final String TAG = CGame.class.getSimpleName();
    public PlayServices playServices;
    public AssetLoader loader;

    public CGame(PlayServices playServices) {
        this.playServices = playServices;
    }

    @Override
    public void create() {
        Gdx.app.log(TAG, "created");
        loader = new AssetLoader();
        loader.load(Gdx.graphics.getWidth());
        setScreen(new SplashScreen(this, loader));
    }

    @Override
    public void dispose() {
        loader.dispose();
        super.dispose();
    }
}
