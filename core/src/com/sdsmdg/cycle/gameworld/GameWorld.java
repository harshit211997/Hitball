package com.sdsmdg.cycle.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.math.Vector2;
import com.sdsmdg.cycle.CGame;
import com.sdsmdg.cycle.TweenAccessors.VectorAccessor;
import com.sdsmdg.cycle.objects.Ball;
import com.sdsmdg.cycle.objects.Bat;
import com.sdsmdg.cycle.objects.Board;
import com.sdsmdg.cycle.objects.Button;
import com.sdsmdg.cycle.objects.Cloud;
import com.sdsmdg.cycle.objects.Fan;
import com.sdsmdg.cycle.objects.Sun;

import java.util.ArrayList;
import java.util.List;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenEquations;
import aurelienribon.tweenengine.TweenManager;

public class GameWorld {

    //This TAG will be used while logging in the app(for debugging purposes)
    //The TAG will be the class name, BTW
    private final String TAG = GameWorld.class.getSimpleName();

    private List<Ball> balls = new ArrayList<Ball>();
    private Bat bat;
    private int screenWidth, screenHeight;
    GameState gameState;
    Button playButton;
    public static int score = 0;
    Preferences prefs;
    private List<Cloud> clouds = new ArrayList<Cloud>();
    private Fan fan;
    private Sun sun;
    private Board board;
    private Button playReady, achievement, leaderBoardButton;
    int hitCount = 0;//This int counts the total no. of hits the bat(or ball) experiences(Including the hit on handle of bat)

    //This reference is just used to call the playServices methods to unlock Achievements
    private CGame game;

    TweenManager manager;//manages position of play button

    /*
    This flag plays a very interesting role in the achievement "Into the Heavens",
    it is true after a collision, but when ball goes above the screen, it becomes false, and the countHeaven value increases
    by 1, then if the value is false in the next collision, collision makes it true, but if it is not,
    then the countHeaven resets.
    Interesting stuff huh!
     */
    boolean flag = false;
    int countHeaven = 0;

    private enum GameState {
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

    public GameWorld(CGame game, int screenWidth, int screenHeight) {
        Vector2 batPosition = new Vector2(screenWidth / 10, 520f / 854 * screenHeight);
        int batHeight = screenWidth / 15;
        int batWidth = (game.loader.batRegion.getRegionWidth() * batHeight) / game.loader.batRegion.getRegionHeight();
        bat = new Bat(batWidth, batHeight, batPosition);
        Ball ball1 = new Ball(screenWidth, screenHeight);
        balls.add(ball1);
        this.screenHeight = screenHeight;
        this.screenWidth = screenWidth;
        gameState = GameState.READY;
        manager = new TweenManager();

        /*
        This play button is used when the game is over
         */
        int replayWidth = screenWidth / 4;
        int replayHeight = screenWidth / 4;
        playButton = new Button(game.loader, this, (screenWidth) / 2, screenHeight + replayHeight / 2,
                replayWidth, replayHeight,
                game.loader.playRegionOn, game.loader.playRegionOff,
                0);

        /*
        This play button is used when game starts at the beginning
         */
        float playWidth = screenWidth / 3, playHeight = screenWidth / 3;
        playReady = new Button(game.loader,
                this, (screenWidth) / 2, (screenHeight) / 2,
                playWidth, playHeight,
                game.loader.playRegionOn,
                game.loader.playRegionOff,
                0);

        //This button is used to show all the achievements of the user
        float achievementWidth = screenWidth / 5, achievementHeight = screenWidth / 5;
        achievement = new Button(game.loader,
                this, screenWidth / 4, screenHeight * 0.75f,
                achievementWidth, achievementHeight,
                game.loader.achievementPressedRegion,
                game.loader.achievementRegion,
                1);

        float leaderWidth = screenWidth / 5, leaderHeight = screenWidth / 5;
        leaderBoardButton = new Button(game.loader,
                this, 3 * screenWidth / 4, screenHeight * 0.75f,
                leaderWidth, leaderHeight,
                game.loader.leaderboardPressedRegion,
                game.loader.leaderboardRegion,
                2);

        float cloudWidth = screenWidth / 3;
        float cloudHeight = (cloudWidth * 121) / 232;
        clouds.add(new Cloud(this, cloudWidth, cloudHeight,
                new Vector2(screenWidth / 10, screenHeight / 20),//cloud's positions
                new Vector2(screenWidth / 2000f, 0),//cloud's velocity
                game.loader.cloudRegion));

        cloudWidth /= 2;
        cloudHeight /= 2;
        clouds.add(new Cloud(this, cloudWidth, cloudHeight,
                new Vector2(screenWidth - cloudWidth, cloudHeight / 2),
                new Vector2(screenWidth / 1500f, 0),
                game.loader.cloud1Region));

        fan = new Fan(this,
                screenWidth / 10, screenWidth / 10,
                new Vector2(screenWidth / 6.8f, screenHeight - screenWidth * 0.86f),
                game.loader.fanRegion);

        sun = new Sun(this,
                new Vector2(screenWidth * 0.75f, screenHeight / 3),
                game.loader.sunRegion);

        board = new Board(
                game.loader,
                this,
                screenWidth / 2, screenWidth / 2,
                new Vector2(screenWidth / 2, screenHeight / 2)
        );

        prefs = Gdx.app.getPreferences("Highscore");

        this.game = game;
    }

    public void setHighScore(int score) {
        game.playServices.submitScore(score);
        prefs.putInteger("highscore", score);
        prefs.flush();
    }

    //This function is used to increase the count of no of games the user has played(This info is useful for unlocking achievement)
    public void incrementGamesPlayed() {
        prefs.putInteger("no_of_times_played", getGamesPlayed() + 1);
        prefs.flush();
    }

    public int getGamesPlayed() {
        return prefs.getInteger("no_of_times_played", 0);
    }

    public boolean isBeginnerComplete() {
        return getGamesPlayed()==10;
    }

    public boolean isBoredComplete() {
        return getGamesPlayed()==100;
    }

    public void update(float delta) {
        manager.update(delta);

        if(isBeginnerComplete()) {
            game.playServices.unlockAchievementBeginner();
        }

        if(isBoredComplete()) {
            game.playServices.unlockAchievementBored();
        }
        /*
        These are the objects that need to be updated in every state of the game
         */
        sun.update(delta);

        for (int i = 0; i < clouds.size(); i++) {
            clouds.get(i).update(delta);
        }

        fan.update(delta);

        /*
        While some other objects need to be updated at a certain state of the game
         */
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

    public int getHighScore() {
        return prefs.getInteger("highscore");
    }

    public void updateReady(float delta) {
        playReady.update(delta);
        achievement.update(delta);
        leaderBoardButton.update(delta);
    }

    public void updateOver(float delta) {
        playButton.update(delta);
        achievement.update(delta);
        leaderBoardButton.update(delta);
    }

    public boolean ballAboveScreen(Ball ball) {
        return ball.getPosition().y <= 0;
    }

    public void incrementCountHeaven() {
        countHeaven ++;
        flag = false;
        if(countHeaven == 3) {
            game.playServices.unlockAchievementIntoHeavens();
        } else if(countHeaven == 4) {
            game.playServices.unlockAchievementYouAreGod();
        }
    }

    public void updateRunning(float delta) {

        bat.update(delta);

        //Update all balls positions on screen
        for (int i = 0; i < balls.size(); i++) {
            balls.get(i).update(delta);
        }

        //There is just one ball therefore balls.get(0) is used
        if(ballAboveScreen(balls.get(0))) {
            if(flag) {
                incrementCountHeaven();
            }
        }

        for (int i = 0; i < balls.size(); i++) {
            Ball ball = balls.get(i);
            if (isColliding(bat, ball, delta) && ball.isInPlane()) {
                playHitSound();

                if(!flag) {
                    flag = true;
                } else {
                    countHeaven = 0;
                }

                setBallOut(ball);
                int distance = getDistance(ball.getPosition().x, bat.getOriginX(), ball.getPosition().y, bat.getOriginY());
                int vBat = 0;
                if (bat.isRotating() && bat.getW() < 0) {
                    vBat = (int) Math.toRadians(bat.getW()) * distance;
                }
                ball.afterCollisionWithBody((int) bat.getRotation(), vBat);
                updateScore();
                increaseHitCount();
            } else if (isCollidingHandle(bat, ball, delta) && ball.isInPlane()) {
                playHitSound();
                setBallOut(ball);
                ball.setOffPlane();
                increaseHitCount();
            }
            //To avoid overlap of bat and ball
            if (ball.isBallOffScreen()) {
                ball.setPosition(screenWidth / 2, screenHeight / 3);
                ball.setRadius(screenWidth / 20);
                setGameStateOver();
            }
        }

    }

    public void increaseHitCount() {
        hitCount ++;
    }

    public void resetHitCount() {
        hitCount = 0;
    }

    public void playHitSound() {
        game.loader.hit.play();//Play the hit sound when the ball hits the bat body and the handle
    }

    public void updateScore() {
        score++;
        if (score > getHighScore()) {
            setHighScore(score);
        }
    }

    public void setBallOut(Ball ball) {
        ball.getPosition().y = (int) ball.getRotatedY(-bat.getRotation(), bat.getOriginX(), bat.getOriginY(), (int) ball.getPosition().x, (int) bat.getPosition().y - ball.getRadius());
    }

    public int getDistance(double x1, double x2, double y1, double y2) {
        return (int) Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }

    /*
    This function checks whether the bat body(bat without the handle) collides with ball or not)
    For checking collision between bat handle and ball there is isCollidingHandle()
     */
    public boolean isColliding(Bat bat, Ball ball, float delta) {

        double rectx = bat.getCenterBodyX();
        double recty = bat.getCenterBodyY();

        double circleDistX = Math.abs(ball.getRotatedX(bat.getRotation(), bat.getOriginX(), bat.getOriginY(), (int) ball.getNextX(delta), (int) ball.getNextY(delta)) - rectx);
        double circleDistY = Math.abs(ball.getRotatedY(bat.getRotation(), bat.getOriginX(), bat.getOriginY(), (int) ball.getNextX(delta), (int) ball.getNextY(delta)) - recty);

        //when ball bottom just touches the bat body(bat without handle)
        if (circleDistX > bat.getWidthWithoutHandle() / 2 + ball.getRadius()) {
            return false;
        }
        if (circleDistY > (bat.getHeight() / 2 + ball.getRadius())) {
            return false;
        }

        if (circleDistX <= (bat.getWidthWithoutHandle() / 2 - ball.getRadius())) {
            return true;
        }
        if (circleDistY <= (bat.getHeight() / 2)) {
            return true;
        }

        double cornerDistanceSq = Math.pow((circleDistX - (bat.getWidthWithoutHandle() / 2 - ball.getRadius()) / 2), 2) +
                Math.pow((circleDistY - bat.getHeight() / 2), 2);

        return (cornerDistanceSq < Math.pow(ball.getRadius(), 2));
    }

    public boolean isCollidingHandle(Bat bat, Ball ball, float delta) {

        double rectx = bat.getCenterHandleX();
        double recty = bat.getCenterHandleY();

        double circleDistX = Math.abs(ball.getRotatedX(bat.getRotation(), bat.getOriginX(), bat.getOriginY(), (int) ball.getNextX(delta), (int) ball.getNextY(delta)) - rectx);
        double circleDistY = Math.abs(ball.getRotatedY(bat.getRotation(), bat.getOriginX(), bat.getOriginY(), (int) ball.getNextX(delta), (int) ball.getNextY(delta)) - recty);

        //when ball bottom just touches the bat body(bat without handle)
        if (circleDistX > bat.getHandleWidth() / 2 + ball.getRadius()) {
            return false;
        }
        if (circleDistY > (bat.getHeight() / 2 + ball.getRadius())) {
            return false;
        }

        if (circleDistX <= (bat.getHandleWidth() / 2 - ball.getRadius())) {
            return true;
        }
        if (circleDistY <= (bat.getHeight() / 2)) {
            return true;
        }

        double cornerDistanceSq = Math.pow((circleDistX - (bat.getHandleWidth() / 2 - ball.getRadius()) / 2), 2) +
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
        achievement.reset();
        leaderBoardButton.reset();
        playButton.reset();//So that it goes to its initial position for reanimating it

        for (int i = 0; i < balls.size(); i++) {
            getBall(i).reset();
        }
        score = 0;
        gameState = GameState.RUNNING;
    }

    public void setGameStateOver() {
        //To animate play button at game over screen
        Tween.registerAccessor(Vector2.class, new VectorAccessor());

        Tween.to(playButton.getPosition(), VectorAccessor.Y, 0.3f).target(screenHeight * 0.8f)
                .ease(TweenEquations.easeInOutExpo)
                .start(manager);

        //To animate achievement button
        achievement.setPosition(screenWidth / 4, screenHeight + achievement.getWidth() / 2);
        Tween.to(achievement.getPosition(), VectorAccessor.Y, 0.3f).target(screenHeight * 0.75f)
                .ease(TweenEquations.easeInOutExpo)
                .start(manager);

        //To animate leaderboard button
        leaderBoardButton.setPosition(3 * screenWidth / 4, screenHeight + achievement.getWidth() / 2);
        Tween.to(leaderBoardButton.getPosition(), VectorAccessor.Y, 0.3f).target(screenHeight * 0.75f)
                .ease(TweenEquations.easeInOutExpo)
                .start(manager);

        bat.onTouchUp();
        Gdx.input.vibrate(300);
        gameState = GameState.OVER;
        incrementGamesPlayed();
        if(score == 2) {
            game.playServices.unlockAchievement2();
        } else if(score == 100) {
            game.playServices.unlockAchievementCentury();
        } else if(score == 50) {
            game.playServices.unlockAchievementHalfCentury();
        }
        if(hitCount == 1) {
            game.playServices.unlockAchievementTrickyOne();
        }
        resetHitCount();
    }

    public int getScore() {
        return score;
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    public List<Cloud> getClouds() {
        return clouds;
    }

    public Fan getFan() {
        return fan;
    }

    public Sun getSun() {
        return sun;
    }

    public Board getBoard() {
        return board;
    }

    public Button getPlayReady() {
        return playReady;
    }

    public Button getAchievementButton() {
        return achievement;
    }

    public CGame getGame() {
        return game;
    }

    public Button getLeaderBoardButton() {
        return leaderBoardButton;
    }
}
