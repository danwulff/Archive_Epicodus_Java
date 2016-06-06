import java.util.Map;
import java.util.HashMap;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;

public class RockPaperScissors {
  public static void main(String[] args) {
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/home.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/result", (request, response) -> {
      HashMap model = new HashMap();
      model.put("template", "templates/result.vtl");
      //stuff here
      String player1 = request.queryParams("player1");
      String player2 = request.queryParams("player2");

      if(Boolean.TRUE.equals(checkWinner(player1, player2))) {
        // used to be checkWinner(player1, player2) == true
        model.put("output", "! Player One");
      }
      else if (Boolean.FALSE.equals(checkWinner(player1, player2))) {
        model.put("output", "! Player Two");
      }
      else {
        model.put("output", "... it's a tie");
      }

      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());
  }

  public static Boolean checkWinner(String player1, String player2) {
    //rock vs scissors
    if (player1.equals("rock") && player2.equals("scissors")) {
      return true;
    }
    //scissors vs paper
    else if (player1.equals("scissors") && player2.equals("paper")) {
      return true;
    }
    //paper vs rock
    else if (player1.equals("paper") && player2.equals("rock")) {
      return true;
    }
    //rock vs paper
    else if (player1.equals("rock") && player2.equals("paper")) {
      return false;
    }
    //scissors vs rock
    else if (player1.equals("scissors") && player2.equals("rock")) {
      return false;
    }
    //paper vs scissors
    else if (player1.equals("paper") && player2.equals("scissors")) {
      return false;
    }
    //else return null (no winner)
    else {
      return null;
    }
  }

}
