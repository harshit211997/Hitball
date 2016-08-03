package com.sdsmdg.cycle;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.sdsmdg.cycle.chelpers.AssetLoader;
import com.sdsmdg.cycle.screens.GameScreen;

public class CGame extends Game{

    private static final String TAG = CGame.class.getSimpleName();

    @Override
    public void create() {
        Gdx.app.log(TAG, "created");
        AssetLoader.load();
        setScreen(new GameScreen());
    }

    @Override
    public void dispose() {
        AssetLoader.dispose();
        super.dispose();
    }
}
