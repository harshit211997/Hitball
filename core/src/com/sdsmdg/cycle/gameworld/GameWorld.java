package com.sdsmdg.cycle.gameworld;

import com.badlogic.gdx.math.Vector2;
import com.sdsmdg.cycle.objects.Ball;
import com.sdsmdg.cycle.objects.Bat;

public class GameWorld {

    private Ball ball;
    private Bat bat;
    private int screenWidth, screenHeight;

    public GameWorld(int screenWidth, int screenHeight) {
        Vector2 batPosition = new Vector2(screenWidth / 10, screenHeight / 2 + 100);
        bat = new Bat(screenHeight / 20, screenWidth / 3 * 2, batPosition);
        ball = new Ball(screenWidth, screenHeight);
        this.screenHeight = screenHeight;
        this.screenWidth = screenWidth;
    }

    public void update(float delta) {
        ball.update(delta);
        bat.update(delta);

        if (isColliding(ball, bat)) {

            ball.setPosition(screenWidth / 2, -ball.getRadius());
            ball.setVelocity(0, 0);

        }
    }

    public boolean isColliding(Ball ball, Bat bat) {

        double rectx = bat.getPosition().x + bat.getWidth() / 2;
        double recty = bat.getPosition().y + bat.getHeight() / 2;

        double circleDistX = Math.abs(ball.getRotatedX(bat.getRotation(), bat.getPosition().x, bat.getPosition().y) - rectx);
        double circleDistY = Math.abs(ball.getRotatedY(bat.getRotation(), bat.getPosition().x, bat.getPosition().y) - recty);

        if (circleDistX > (bat.getWidth() / 2 + ball.getRadius())) {
            return false;
        }
        if (circleDistY > (bat.getHeight() / 2 + ball.getRadius())) {
            return false;
        }

        if (circleDistX <= (bat.getWidth() / 2)) {
            return true;
        }
        if (circleDistY <= (bat.getHeight() / 2)) {
            return true;
        }

        double cornerDistanceSq = Math.pow((circleDistX - bat.getWidth() / 2), 2) +
                Math.pow((circleDistY - bat.getHeight() / 2), 2);

        return (cornerDistanceSq <= Math.pow(ball.getRadius(), 2));
    }

    public Ball getBall() {
        return ball;
    }

    public Bat getBat() {
        return bat;
    }

}
