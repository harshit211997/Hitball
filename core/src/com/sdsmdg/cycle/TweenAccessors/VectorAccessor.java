package com.sdsmdg.cycle.TweenAccessors;

import com.badlogic.gdx.math.Vector2;

import aurelienribon.tweenengine.TweenAccessor;

public class VectorAccessor implements TweenAccessor<Vector2> {

    public static int X = 0, Y = 1;

    @Override
    public int getValues(Vector2 target, int tweenType, float[] returnValues) {
        if(tweenType == X) {
            returnValues[0] = target.x;
        } else if(tweenType == Y) {
            returnValues[0] = target.y;
        }
        return 1;
    }

    @Override
    public void setValues(Vector2 target, int tweenType, float[] newValues) {
        if(tweenType == X) {
            target.set(newValues[0], target.y);
        } else if(tweenType == Y) {
            target.set(target.x, newValues[0]);
        }
    }
}
