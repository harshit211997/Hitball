package com.sdsmdg.cycle;

public interface PlayServices {

    public void signIn();
    public void signOut();
    public void rateGame();
    public void unlockAchievementBeginner();
    public void unlockAchievement2();
    public void unlockAchievementTrickyOne();
    public void unlockAchievementCentury();
    public void unlockAchievementHalfCentury();
    public void unlockAchievementBored();
    public void unlockAchievementIntoHeavens();
    public void unlockAchievementYouAreGod();
    public void submitScore(int highScore);
    public void showAchievement();
    public void showScore();
    public boolean isSignedIn();

}
