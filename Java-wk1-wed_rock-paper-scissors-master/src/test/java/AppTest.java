import org.fluentlenium.adapter.FluentTest;
import org.junit.ClassRule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import static org.assertj.core.api.Assertions.assertThat;

import static org.fluentlenium.core.filter.FilterConstructor.*;

public class AppTest extends FluentTest {
  public WebDriver webDriver = new HtmlUnitDriver();

  @Override
  public WebDriver getDefaultDriver() {
    return webDriver;
  }

  @ClassRule
  public static ServerRule server = new ServerRule();


  @Test
  public void rootTest() {
    goTo("http://localhost:4567/");
    assertThat(pageSource()).contains("rock paper scissors");
  }

  @Test
  public void isATie() {
    goTo("http://localhost:4567");
    submit(".btn");
    assertThat(pageSource()).contains("it's a tie");
  }

  @Test
  public void player1Wins() {
    goTo("http://localhost:4567");
    click("#player2_scissor");
    submit(".btn");
    assertThat(pageSource()).contains("Player One");
  }

  @Test
  public void player2Wins() {
    goTo("http://localhost:4567");
    click("#player1_scissor");
    submit(".btn");
    assertThat(pageSource()).contains("Player Two");
  }
}
