import org.junit.*;
import static org.junit.Assert.*;

public class RockPaperScissorsTest {

  @Test //test rock vs scissors
  public void checkWinner_forPlayer1RockPlayer2Scissors_true() {
    RockPaperScissors game = new RockPaperScissors();
    assertEquals(true, game.checkWinner("rock", "scissors"));
  }

  @Test //test scissors vs paper
  public void checkWinner_forPlayer1ScissorsPlayer2Paper_true() {
    RockPaperScissors game = new RockPaperScissors();
    assertEquals(true, game.checkWinner("scissors", "paper"));
  }

  @Test //test paper vs rock
  public void checkWinner_forPlayer1PaperPlayer2Rock_true() {
    RockPaperScissors game = new RockPaperScissors();
    assertEquals(true, game.checkWinner("paper", "rock"));
  }

  @Test //test rock vs paper
  public void checkWinner_forPlayer1RockPlayer2Paper_false() {
    RockPaperScissors game = new RockPaperScissors();
    assertEquals(false, game.checkWinner("rock", "paper"));
  }

  @Test //test scissors vs rock
  public void checkWinner_forPlayer1ScissorsPlayer2Rock_false() {
    RockPaperScissors game = new RockPaperScissors();
    assertEquals(false, game.checkWinner("scissors", "rock"));
  }

  @Test //test paper vs scissors
  public void checkWinner_forPlayer1PaperPlayer2Scissors_false() {
    RockPaperScissors game = new RockPaperScissors();
    assertEquals(false, game.checkWinner("paper", "scissors"));
  }

  @Test //test rock vs rock
  public void checkWinner_forPlayer1RockPlayer2Rock_true() {
    RockPaperScissors game = new RockPaperScissors();
    assertEquals(null, game.checkWinner("rock", "rock"));
  }
  
  //do we need to test paper vs paper or scissors vs scissors?
}
