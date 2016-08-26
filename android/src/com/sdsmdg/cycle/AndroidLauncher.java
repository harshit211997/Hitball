package com.sdsmdg.cycle;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.GamesActivityResultCodes;
import com.google.example.games.basegameutils.GameHelper;
import com.sdsmdg.ball.R;


public class AndroidLauncher extends AndroidApplication implements PlayServices {

    private GameHelper gameHelper;
    private final static int requestCode = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
        initialize(new CGame(this), config);

        gameHelper = new GameHelper(this, GameHelper.CLIENT_GAMES);
        gameHelper.enableDebugLog(true);

        GameHelper.GameHelperListener gameHelperListener = new GameHelper.GameHelperListener() {
            @Override
            public void onSignInFailed() {
                Log.i("AndroidLauncher", "Sign in Failed!");
            }

            @Override
            public void onSignInSucceeded() {
                Log.i("AndroidLauncher", "Sign in Successful!");
            }
        };

        gameHelper.setup(gameHelperListener);
    }

    @Override
    protected void onStart() {
        super.onStart();
        gameHelper.onStart(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        gameHelper.onStop();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        gameHelper.onActivityResult(requestCode, resultCode, data);

        if (resultCode == GamesActivityResultCodes.RESULT_RECONNECT_REQUIRED){
            // force a disconnect to sync up state, ensuring that mClient reports "not connected"
            gameHelper.getApiClient().disconnect();
        }
    }

    @Override
    public void signIn() {
        try {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    gameHelper.beginUserInitiatedSignIn();
                }
            });
        } catch (Exception e) {
            Log.i("MainActivity", "Log in failed: " + e.getMessage() + ".");
        }
    }

    @Override
    public void signOut() {
        try {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    gameHelper.signOut();
                }
            });
        } catch (Exception e) {
            Log.i("MainActivity", "Log out failed: " + e.getMessage() + ".");
        }
    }

    @Override
    public void rateGame() {
        String str = "Your PlayStore Link";
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(str)));
    }

    @Override
    public void unlockAchievementBeginner() {
        //This achievement is named Beginner, for playing 10 games
        if (gameHelper.isSignedIn())
            Games.Achievements.unlock(gameHelper.getApiClient(),
                    getString(R.string.achievement_beginner));
    }

    @Override
    public void unlockAchievement2() {
        //This gets unlocked on making a score of 2
        if (gameHelper.isSignedIn())
            Games.Achievements.unlock(gameHelper.getApiClient(),
                    getString(R.string.achievement_welcome_to_the_2_group));
    }

    @Override
    public void unlockAchievementTrickyOne() {
        //This gets unlocked when the ball hits the bat only once in a game
        if (gameHelper.isSignedIn())
            Games.Achievements.unlock(gameHelper.getApiClient(),
                    getString(R.string.achievement_the_tricky_one));
    }

    @Override
    public void unlockAchievementCentury() {
        if (gameHelper.isSignedIn())
            Games.Achievements.unlock(gameHelper.getApiClient(),
                    getString(R.string.achievement_century));
    }

    @Override
    public void unlockAchievementHalfCentury() {
        if (gameHelper.isSignedIn())
            Games.Achievements.unlock(gameHelper.getApiClient(),
                    getString(R.string.achievement_half_century));
    }

    @Override
    public void unlockAchievementBored() {
        //Play 100 games to unlock this achievement
        if (gameHelper.isSignedIn())
            Games.Achievements.unlock(gameHelper.getApiClient(),
                    getString(R.string.achievement_bored));
    }

    @Override
    public void unlockAchievementIntoHeavens() {
        //hit the ball such that it goes out of the screen 3 times continuously without dying
        if (gameHelper.isSignedIn()) {
            Games.Achievements.unlock(gameHelper.getApiClient(),
                    getString(R.string.achievement_into_the_heavens));
        }

    }

    @Override
    public void unlockAchievementYouAreGod() {
        //hit the ball such that it goes out of the screen 4 times continuously without dying
        if (gameHelper.isSignedIn()) {
            Games.Achievements.unlock(gameHelper.getApiClient(),
                    getString(R.string.achievement_you_are_the_god));
        }
    }

    @Override
    public void submitScore(int highScore) {
        if (isSignedIn() == true) {
            Games.Leaderboards.submitScore(gameHelper.getApiClient(),
                    getString(R.string.leaderboard_leaderboard), highScore);
        }
    }

    @Override
    public void showAchievement() {
        if (isSignedIn() == true) {
            startActivityForResult(Games.Achievements.getAchievementsIntent(gameHelper.getApiClient()), requestCode);
        } else {
            signIn();
        }
    }

    @Override
    public void showScore() {
        if (isSignedIn() == true) {
            startActivityForResult(Games.Leaderboards.getLeaderboardIntent(gameHelper.getApiClient(),
                    getString(R.string.leaderboard_leaderboard)), requestCode);
        } else {
            signIn();
        }
    }

    @Override
    public boolean isSignedIn() {
        return gameHelper.isSignedIn();
    }


}
