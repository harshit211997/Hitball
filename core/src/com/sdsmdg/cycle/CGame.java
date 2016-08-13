package com.sdsmdg.cycle;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.TimeUtils;
import com.sdsmdg.cycle.chelpers.AssetLoader;
import com.sdsmdg.cycle.screens.SplashScreen;

public class CGame extends Game{

    private static final String TAG = CGame.class.getSimpleName();
    private long startTime;

    @Override
    public void create() {
        Gdx.app.log(TAG, "created");
        AssetLoader.load();
        startTime = TimeUtils.millis();
        setScreen(new SplashScreen(this));
    }

    @Override
    public void dispose() {
        AssetLoader.dispose();
        super.dispose();
    }
}
