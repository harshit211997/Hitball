package com.sdsmdg.cycle.objects;

import com.badlogic.gdx.math.Vector2;

public class Ball {

    Vector2 position;
    Vector2 velocity = new Vector2(0, 0);
    Vector2 acceleration = new Vector2(0, 480);
    int radius;
    int screenWidth, screenHeight;

    public Ball(int screenWidth, int screenHeight) {
        this.screenHeight = screenHeight;
        this.screenWidth = screenWidth;
        position = new Vector2(screenWidth / 2, 0);
        radius = screenWidth / 20;
    }

    public void update(float delta) {
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

    public double getRotatedX(float a, float originX, float originY) {
        float angle = -a;
        double rotatedX = Math.cos(Math.toRadians(angle)) * (position.x - originX) - Math.sin(Math.toRadians(angle)) * (position.y - originY) + originX;
        return rotatedX;
    }

    public double getRotatedY(float a, float originX, float originY) {
        float angle = -a;
        double rotatedY = Math.sin(Math.toRadians(angle)) * (position.x - originX) + Math.cos(Math.toRadians(angle)) * (position.y - originY) + originY;
        return rotatedY;
    }

    public void setPosition(int x, int y) {
        position.x = x;
        position.y = y;
    }

    public void setVelocity(int vx, int vy) {
        velocity.x = vx;
        velocity.y = vy;
    }
}
