import java.util.Random;

public class Tamagotchi {
  private int mLifeTime;
  private int mFoodLevel;
  private int mTiredLevel;
  private int mActivityLevel;
  private String mName;
  private String mState;

  public Tamagotchi (String name) {
    mName = name;
    mLifeTime = 0;
    mState = "baby";    //also "child", "teen", "adult", "dead"
    mFoodLevel = 8;
    mTiredLevel = 5;
    mActivityLevel = 5;
  }
  //Status stuff---------------------------------
  public String getName() {
    return mName;
  }
  public boolean isAlive() {
    if(mFoodLevel < 1) {
      mState = "dead";
    }

    if (mState == "dead") {
      return false;
    }
    else {
      return true;
    }
  }
  public String getState() {
    return mState;
  }
  public int getLifeTime() {
    return mLifeTime;
  }
  //Food level changing--------------------------
  public int getFoodLevel() {
    return mFoodLevel;
  }
  public void setFoodLevel(int level) {
    mFoodLevel = level;
  }
  public void feed() {
    //random stuff
    Random randomNumber = new Random();
    int randomInt = randomNumber.nextInt(3) + 1;
    mFoodLevel += randomInt;
  }

  //Tired level changing-------------------------
  public int getTiredLevel() {
    return mTiredLevel;
  }
  public void setTiredLevel(int level) {
    mTiredLevel = level;
  }
  public void sleep() {
    //random stuff
    Random randomNumber = new Random();
    int randomInt = randomNumber.nextInt(3) + 1;
    mTiredLevel -= randomInt;
  }

  //Activity level changing----------------------
  public int getActivityLevel() {
    return mActivityLevel;
  }
  public void setActivityLevel(int level) {
    mActivityLevel = level;
  }
  public void play() {
    //random stuff
    Random randomNumber = new Random();
    int randomInt = randomNumber.nextInt(3) + 1;
    mActivityLevel += randomInt;
  }

  //Time changing--------------------------------
  public void timePasses() {
    //change lifetime (depends on levels)
    if (mFoodLevel >= 8 && mTiredLevel <= 2) {  //fast growth
      mLifeTime += 5;
    }
    else if (mFoodLevel >= 6 && mTiredLevel <= 4) { //medium growth
      mLifeTime += 3;
    }
    else if (mFoodLevel >= 4 && mTiredLevel <= 6) { //slow growth
      mLifeTime += 1;
    }
    else {} //no growth

    //check for state change (baby -> child etc)
    if (mState != "dead") {
      if (mLifeTime >= 50 && mLifeTime < 100) {
        mState = "child";
      }
      else if (mLifeTime >= 100 && mLifeTime < 180) {
        mState = "teen";
      }
      else if (mLifeTime >= 180 && mLifeTime < 250) {
        mState = "adult";
      }
      else if (mLifeTime >= 250) {
        mState = "dead";
      }
    }

    //update levels
    if (mActivityLevel > 8) {
      mTiredLevel += 3;
    }
    else if (mActivityLevel > 5) {
      mTiredLevel += 2;
    }
    else {
      mTiredLevel += 1;
    }

    mFoodLevel -= 1;
    mActivityLevel -= 1;
  }

  public void changeLifeTime(int lifeTime) {
    mLifeTime = lifeTime;
  }
}
