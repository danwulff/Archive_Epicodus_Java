import org.junit.*;
import static org.junit.Assert.*;

public class TriangleTest {

  @Test
  public void newTriangle_instatiatesCorrectly() {
    Triangle testTriangle = new Triangle(2,3,4);
    assertEquals(true, testTriangle instanceof Triangle);
  }

  @Test
  public void getSide1_returnSide1() {
    Triangle testTriangle = new Triangle(2,3,4);
    assertEquals(2, testTriangle.getSide1());
  }

  @Test
  public void getSide2_returnSide2() {
    Triangle testTriangle = new Triangle(2,3,4);
    assertEquals(3, testTriangle.getSide2());
  }

  @Test
  public void getSide3_returnSide3() {
    Triangle testTriangle = new Triangle(2,3,4);
    assertEquals(4, testTriangle.getSide3());
  }

  @Test
  public void typeOfTriangle_isNotATriangle() {
    Triangle testTriangle = new Triangle(100,3,4);
    assertEquals("not a triangle", testTriangle.typeOfTriangle());
  }

  @Test
  public void typeOfTriangle_isEquilateral() {
    Triangle testTriangle = new Triangle(3,3,3);
    assertEquals("equilateral", testTriangle.typeOfTriangle());
  }

  @Test
  public void typeOfTriangle_isIsosceles() {
    Triangle testTriangle = new Triangle(3,3,2);
    assertEquals("isosceles", testTriangle.typeOfTriangle());
  }

  @Test
  public void typeOfTriangle_isScalene() {
    Triangle testTriangle = new Triangle(3,4,2);
    assertEquals("scalene", testTriangle.typeOfTriangle());
  }


}
