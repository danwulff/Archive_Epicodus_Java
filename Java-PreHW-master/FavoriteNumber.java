import java.io.Console;

public class FavoriteNumber {
  public static void main(String[] args) {
    Console myConsole = System.console();

    System.out.println("What is your favorite number?");
    String favoriteNumber = myConsole.readLine();
    System.out.println("Your favorite number is " + favoriteNumber + "? Me too!");

  }
}
