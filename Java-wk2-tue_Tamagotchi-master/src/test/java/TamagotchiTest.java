import org.junit.*;
import static org.junit.Assert.*;

public class TamagotchiTest {

  @Test
  public void Tamagotchi_instantiatesCorrectly() {
    Tamagotchi testPet = new Tamagotchi("name");
    assertEquals("name", testPet.getName());
    assertEquals(8, testPet.getFoodLevel());
    assertEquals(5, testPet.getTiredLevel());
    assertEquals(5, testPet.getActivityLevel());
  }

  @Test
  public void timePasses_foodDecreasesByOne_7() {
    Tamagotchi testPet = new Tamagotchi("name");
    testPet.timePasses();
    assertEquals(7, testPet.getFoodLevel());
  }

  @Test
  public void isAlive_foodLevelAbove0_true() {
    Tamagotchi testPet = new Tamagotchi("name");
    assertEquals(true, testPet.isAlive());
  }

  @Test
  public void isAlive_foodBelow1_false() {
    Tamagotchi testPet = new Tamagotchi("name");
    testPet.setFoodLevel(0);
    assertEquals(false, testPet.isAlive());
  }

  //how fast they grow depends on levels
  @Test
  public void growRate_Fast_5() {
    Tamagotchi testPet = new Tamagotchi("name");
    testPet.setFoodLevel(8);
    testPet.setTiredLevel(2);
    testPet.timePasses();
    assertEquals(5, testPet.getLifeTime());
  }
  @Test
  public void growRate_Medium_3() {
    Tamagotchi testPet = new Tamagotchi("name");
    testPet.setFoodLevel(6);
    testPet.setTiredLevel(4);
    testPet.timePasses();
    assertEquals(3, testPet.getLifeTime());
  }
  @Test
  public void growRate_Slow_1() {
    Tamagotchi testPet = new Tamagotchi("name");
    testPet.setFoodLevel(4);
    testPet.setTiredLevel(6);
    testPet.timePasses();
    assertEquals(1, testPet.getLifeTime());
  }
  @Test
  public void growRate_NotGrow_0() {
    Tamagotchi testPet = new Tamagotchi("name");
    testPet.setFoodLevel(3);
    testPet.setTiredLevel(10);
    testPet.timePasses();
    assertEquals(0, testPet.getLifeTime());
  }

  //activity level causes tiredness to change
  @Test
  public void tiredLevel_ActivityLevel9_3() {
    Tamagotchi testPet = new Tamagotchi("name");
    testPet.setActivityLevel(9);
    testPet.setTiredLevel(0);
    testPet.timePasses();
    assertEquals(3, testPet.getTiredLevel());
  }
  //activity level changes with play
  @Test
  public void tiredLevel_ActivityLevel6_2() {
    Tamagotchi testPet = new Tamagotchi("name");
    testPet.setActivityLevel(6);
    testPet.setTiredLevel(0);
    testPet.timePasses();
    assertEquals(2, testPet.getTiredLevel());
  }
  @Test
  public void tiredLevel_ActivityLevel3_1() {
    Tamagotchi testPet = new Tamagotchi("name");
    testPet.setActivityLevel(3);
    testPet.setTiredLevel(0);
    testPet.timePasses();
    assertEquals(1, testPet.getTiredLevel());
  }
  //tired level changes with sleep
  @Test
  public void sleep_TiredLevel_true() {
    Tamagotchi testPet = new Tamagotchi("name");
    boolean temp = true;
    for(int i = 0; i < 10000; i++) {
      testPet.setTiredLevel(10);
      testPet.sleep();
      if (testPet.getTiredLevel() <= 9 && testPet.getTiredLevel() >= 7) {}
      else {
        temp = false;
      }
    }
    assertEquals(true, temp);
  }
  //food level changes with feed
  @Test
  public void feed_FoodLevel_true() {
    Tamagotchi testPet = new Tamagotchi("name");
    boolean temp = true;
    for(int i = 0; i < 10000; i++) {
      testPet.setFoodLevel(0);
      testPet.feed();
      if (testPet.getFoodLevel() <= 3 && testPet.getFoodLevel() >= 1) {}
      else {
        temp = false;
      }
    }
    assertEquals(true, temp);
  }
  //Activity level changes with play
  @Test
  public void feed_ActivityLevel_true() {
    Tamagotchi testPet = new Tamagotchi("name");
    boolean temp = true;
    for(int i = 0; i < 10000; i++) {
      testPet.setActivityLevel(0);
      testPet.play();
      if (testPet.getActivityLevel() <= 3 && testPet.getActivityLevel() >= 1) {}
      else {
        temp = false;
      }
    }
    assertEquals(true, temp);
  }
  //baby to child
  @Test
  public void state_LifeTime50_child() {
    Tamagotchi testPet = new Tamagotchi("name");
    testPet.changeLifeTime(50);
    testPet.timePasses();
    assertEquals("child", testPet.getState());
  }
  //child to teen
  @Test
  public void state_LifeTime100_teen() {
    Tamagotchi testPet = new Tamagotchi("name");
    testPet.changeLifeTime(100);
    testPet.timePasses();
    assertEquals("teen", testPet.getState());
  }
  //teen to adult
  @Test
  public void state_LifeTime180_adult() {
    Tamagotchi testPet = new Tamagotchi("name");
    testPet.changeLifeTime(180);
    testPet.timePasses();
    assertEquals("adult", testPet.getState());
  }
  //adult to dead
  @Test
  public void state_LifeTime250_dead() {
    Tamagotchi testPet = new Tamagotchi("name");
    testPet.changeLifeTime(250);
    testPet.timePasses();
    assertEquals("dead", testPet.getState());
  }
}
