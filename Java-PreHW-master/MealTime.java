import java.io.Console;

public class MealTime {
  public static void main(String[] args) {
    askWhatYouAteFor("breakfast");
    askWhatYouAteFor("lunch");
    askWhatYouAteFor("dinner");
  }

  public static void askWhatYouAteFor(String meal) {
    Console myConsole = System.console();
    System.out.println("What did you eat for " + meal + "?");
    String yourMeal = myConsole.readLine();
    System.out.println("You had " + yourMeal + " for " + meal + ".");
  }
}
