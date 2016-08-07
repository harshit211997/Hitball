package com.sdsmdg.cycle.objects;

import com.badlogic.gdx.math.Vector2;

public class Ball {

    private Vector2 position;
    private Vector2 velocity = new Vector2(0, 0);
    private Vector2 acceleration = new Vector2(0, 1500);
    int radius;
    int screenWidth, screenHeight;
    float e = 0.8f;

    public Ball(int screenWidth, int screenHeight) {
        this.screenHeight = screenHeight;
        this.screenWidth = screenWidth;
        position = new Vector2(screenWidth / 2, screenHeight / 3);
        radius = screenWidth / 20;
    }

    public void update(float delta) {
        position.x += velocity.x * delta;
        velocity.y += acceleration.y * delta;
        position.y += velocity.y * delta;
    }

    public int getRadius() {
        return radius;
    }

    //Refer to http://www.migapro.com/circle-and-rotated-rectangle-collision-detection/ for more info about collision detection

    public Vector2 getPosition() {
        return position;
    }

    public void afterCollision(int a, int vBat) {
        velocity.x = (float)getRotatedX(a, 0, 0, (int)velocity.x, (int)velocity.y);
        velocity.y = Math.min(vBat - e * (float)Math.abs(getRotatedY(a, 0, 0, (int)velocity.x, (int)velocity.y)), -600);
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
        if(position.x < -radius || position.x > screenWidth + radius || position.y >= screenHeight + radius) {
            return true;
        }
        return false;
    }

    public void reset() {
        setVelocity(0, 0);
        setPosition(screenWidth / 2, screenHeight / 3);
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
}
