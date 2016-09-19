package com.sdsmdg.cycle.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.sdsmdg.cycle.chelpers.AssetLoader;

public class Ball {

    private Vector2 position;
    private Vector2 velocity = new Vector2(0, 0);
    private Vector2 acceleration;
    int radius;
    int screenWidth, screenHeight;
    float e = 0.8f;
    private boolean isInPlane = true;
    private float rotation = 0;
    private float w = 100;
    private float re = 0.2f;//Rotation analogy of e(coefficient of restitution

    /*this property of ball determines that after collision with bat handle,
    the ball will go towards the screen or away from it
    */
    private boolean towardsScreen = true;

    public Ball(int screenWidth, int screenHeight) {
        this.screenHeight = screenHeight;
        this.screenWidth = screenWidth;
        position = new Vector2(screenWidth / 2, screenHeight / 3);
        radius = screenWidth / 20;

        acceleration = new Vector2(0, screenWidth * 3.5f);
    }

    public void update(float delta) {
        position.x += velocity.x * delta;
        velocity.y += acceleration.y * delta;
        position.y += velocity.y * delta;
        rotation += w * delta;

        if (!isInPlane()) {
            if (towardsScreen)
                radius+=screenWidth / 480;
            else
                radius -= screenWidth / 960;
        }

        //reset the rotation value after one rotation
        if(rotation >= 360) {
            rotation = 0;
        }

    }
    
    public void onDraw(SpriteBatch batcher) {
        batcher.draw(AssetLoader.ballRegion, position.x - radius, position.y - radius,
                radius, radius,
                radius * 2, radius * 2,
                1, 1,
                rotation);
    }

    public int getRadius() {
        return radius;
    }

    //Refer to http://www.migapro.com/circle-and-rotated-rectangle-collision-detection/ for more info about collision detection

    public Vector2 getPosition() {
        return position;
    }

    public void afterCollisionWithBody(int a, int vBat) {
        readjustW();
        velocity.x = (float) getRotatedX(a, 0, 0, (int) velocity.x, (int) velocity.y);
        velocity.y = Math.min(vBat - e * (float) Math.abs(getRotatedY(a, 0, 0, (int) velocity.x, (int) velocity.y)), -screenWidth * 1.5f);
    }

    /* This method adjusts w according to the speed of ball and its direction of
    movement, although it is not as good as physics in real life, but it looks good enough
     */
    public void readjustW() {
        if(velocity.x < 0) {
            w = re * getSpeed() / screenWidth * 480;
        }
        else {
            w = -re * getSpeed() / screenWidth * 480;
        }
    }

    public float getSpeed() {
        return (float)Math.sqrt(Math.pow(velocity.y, 2) + Math.pow(velocity.x, 2));
    }

    public double getRotatedX(float a, float originX, float originY, int x, int y) {
        float angle = -a;
        double rotatedX = Math.cos(Math.toRadians(angle)) * (x - originX) - Math.sin(Math.toRadians(angle)) * (y - originY) + originX;
        return rotatedX;
    }

    public double getRotatedY(float a, float originX, float originY, int x, int y) {
        float angle = -a;
        double rotatedY = Math.sin(Math.toRadians(angle)) * (x - originX) + Math.cos(Math.toRadians(angle)) * (y - originY) + originY;
        return rotatedY;
    }

    public boolean isBallOffScreen() {
        if (position.x < -radius || position.x > screenWidth + radius || position.y >= screenHeight + radius) {
            return true;
        }
        return false;
    }

    public void reset() {
        isInPlane = true;
        radius = screenWidth / 20;
        setVelocity(0, 0);
    }

    public void setPosition(int x, int y) {
        position.x = x;
        position.y = y;
    }

    public void setVelocity(int vx, int vy) {
        velocity.x = vx;
        velocity.y = vy;
    }

    public double getNextX(float delta) {
        return position.x + velocity.x * delta;
    }

    public double getNextY(float delta) {
        return position.y + (velocity.y + acceleration.y * delta) * delta;
    }

    public boolean isInPlane() {
        return isInPlane;
    }

    /*when the ball goes out of plane, it means that,
     it must have collided with the bat handle,
     so its vertical component of velocity must be changed
     (here I make it zero)
     */
    public void setOffPlane() {
        towardsScreen = getRandomBool();
        velocity.y = -screenWidth / 5;
        isInPlane = false;
    }

    //This function return only true currently, because of drawing order of bat and ball
    //Will be fixed soon
    public boolean getRandomBool() {
        return true;
    }

    public float getRotation() {
        return rotation;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }
}
