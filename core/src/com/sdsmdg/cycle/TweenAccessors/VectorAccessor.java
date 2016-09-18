package com.sdsmdg.cycle.TweenAccessors;

import com.badlogic.gdx.math.Vector2;

import aurelienribon.tweenengine.TweenAccessor;

public class VectorAccessor implements TweenAccessor<Vector2> {

    @Override
    public int getValues(Vector2 target, int tweenType, float[] returnValues) {
        returnValues[0] = target.x;
        returnValues[1] = target.y;
        return 1;
    }

    @Override
    public void setValues(Vector2 target, int tweenType, float[] newValues) {
        target.set(newValues[0], newValues[1]);
    }
}
