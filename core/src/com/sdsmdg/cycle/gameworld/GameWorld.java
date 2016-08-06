package com.sdsmdg.cycle.gameworld;

import com.badlogic.gdx.math.Vector2;
import com.sdsmdg.cycle.chelpers.AssetLoader;
import com.sdsmdg.cycle.objects.Ball;
import com.sdsmdg.cycle.objects.Bat;
import com.sdsmdg.cycle.objects.Button;

import java.util.ArrayList;
import java.util.List;

public class GameWorld {

    private List<Ball> balls = new ArrayList<Ball>();
    private Bat bat;
    private int screenWidth, screenHeight;
    GameState gameState;
    Button playButton;

    private enum GameState{
        READY, RUNNING, OVER
    }

    public boolean isReady() {
        return gameState == GameState.READY;
    }

    public boolean isRunning() {
        return gameState == GameState.RUNNING;
    }

    public boolean isOver() {
        return gameState == GameState.OVER;
    }

    public GameWorld(int screenWidth, int screenHeight) {
        Vector2 batPosition = new Vector2(screenWidth / 10, screenHeight / 2 + 100);
        int batHeight = screenWidth / 15;
        int batWidth = (AssetLoader.batRegion.getRegionWidth() * batHeight ) / AssetLoader.batRegion.getRegionHeight();
        bat = new Bat(batWidth, batHeight, batPosition);
        Ball ball1 = new Ball(screenWidth, screenHeight);
        balls.add(ball1);
        this.screenHeight = screenHeight;
        this.screenWidth = screenWidth;
        gameState = GameState.READY;
        playButton = new Button(this, screenWidth / 3, AssetLoader.playRegion, screenWidth / 3, screenWidth / 2, screenHeight / 2);

    }

    public void update(float delta) {
        switch (gameState) {
            case READY:
                updateReady(delta);
                break;
            case OVER:
                updateOver(delta);
                break;
            default:
                updateRunning(delta);
        }
    }

    public void updateReady(float delta) {
        //To be filled soon enough
    }

    public void updateOver(float delta) {
        //Agh... Same as above
    }

    public void updateRunning(float delta) {
        //Update all balls positions on screen
        for (int i = 0; i < balls.size(); i++) {
            balls.get(i).update(delta);
        }
        //Update the bat rotations
        bat.update(delta);
        for (int i = 0; i < balls.size(); i++) {
            Ball ball = balls.get(i);
            if (isColliding(ball, bat, delta)) {
                setBallOut(ball);
                int distance = getDistance(ball.getPosition().x, bat.getOriginX(), ball.getPosition().y, bat.getOriginY());
                int vBat = 0;
                if(bat.isRotating() && bat.getW() < 0 ) {
                    vBat = (int) Math.toRadians(bat.getW()) * distance;
                }
                ball.afterCollision((int) bat.getRotation(), vBat);
            }
            //To avoid overlap of bat and ball
            if (ball.isBallOffScreen()) {
                ball.reset();
            }
        }

    }

    public void setBallOut(Ball ball) {
        ball.getPosition().y = (int)ball.getRotatedY(-bat.getRotation(), bat.getOriginX(), bat.getOriginY(), (int)ball.getPosition().x, (int)bat.getPosition().y - ball.getRadius());
    }

    public int getDistance(double x1, double x2, double y1, double y2) {
        return (int) Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }

    public boolean isColliding(Ball ball, Bat bat, float delta) {

        double rectx = bat.getPosition().x + bat.getHandleWidth() + bat.getWidthWithoutHandle() / 2;
        double recty = bat.getPosition().y + bat.getHeight() / 2;

        double circleDistX = Math.abs(ball.getRotatedX(bat.getRotation(), bat.getOriginX(), bat.getOriginY(), (int) ball.getNextX(delta), (int) ball.getNextY(delta)) - rectx);
        double circleDistY = Math.abs(ball.getRotatedY(bat.getRotation(), bat.getOriginX(), bat.getOriginY(), (int) ball.getNextX(delta), (int) ball.getNextY(delta)) - recty);

        if (circleDistX > ((bat.getWidthWithoutHandle() - ball.getRadius()) / 2 + ball.getRadius())) {
            return false;
        }
        if (circleDistY > (bat.getHeight() / 2 + ball.getRadius())) {
            return false;
        }

        if (circleDistX <= ((bat.getWidthWithoutHandle() - ball.getRadius()) / 2)) {
            return true;
        }
        if (circleDistY <= (bat.getHeight() / 2)) {
            return true;
        }

        double cornerDistanceSq = Math.pow((circleDistX - (bat.getWidthWithoutHandle() - ball.getRadius()) / 2), 2) +
                Math.pow((circleDistY - bat.getHeight() / 2), 2);

        return (cornerDistanceSq < Math.pow(ball.getRadius(), 2));
    }

    public Ball getBall(int i) {
        return balls.get(i);
    }

    public Bat getBat() {
        return bat;
    }

    public Button getPlayButton() {
        return playButton;
    }

    public void setGameStateRunning() {
        gameState = GameState.RUNNING;
    }
}
