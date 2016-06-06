import org.fluentlenium.adapter.FluentTest;
import org.junit.ClassRule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import static org.assertj.core.api.Assertions.assertThat;

public class AppTest extends FluentTest {
  public WebDriver webDriver = new HtmlUnitDriver();

  @Override
  public WebDriver getDefaultDriver() {
    return webDriver;
  }

  @ClassRule
  public static ServerRule server = new ServerRule();

  @Test public void rootTest() {
    goTo("http://localhost:4567/");
    assertThat(pageSource()).contains("Triangle thinga-jig");
  }

  @Test public void equilateralTest() {
    goTo("http://localhost:4567/");
    fill("#side1").with("2");
    fill("#side2").with("2");
    fill("#side3").with("2");
    submit(".btn");
    assertThat(pageSource()).contains("equilateral");
  }

  @Test public void isoscelesTest() {
    goTo("http://localhost:4567/");
    fill("#side1").with("2");
    fill("#side2").with("2");
    fill("#side3").with("3");
    submit(".btn");
    assertThat(pageSource()).contains("isosceles");
  }

  @Test public void scaleneTest() {
    goTo("http://localhost:4567/");
    fill("#side1").with("2");
    fill("#side2").with("3");
    fill("#side3").with("4");
    submit(".btn");
    assertThat(pageSource()).contains("scalene");
  }

  @Test public void invalidTriangleTest() {
    goTo("http://localhost:4567/");
    fill("#side1").with("12093");
    fill("#side2").with("2");
    fill("#side3").with("2");
    submit(".btn");
    assertThat(pageSource()).contains("not a triangle");
  }

}
