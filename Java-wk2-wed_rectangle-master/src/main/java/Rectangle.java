public class Rectangle {
  private int mlength;
  private int mwidth;

  public Rectangle(int length, int width) {
    mlength = length;
    mwidth = width;
  }

  public int getLength() {
    return mlength;
  }

  public int getWidth() {
    return mwidth;
  }

  public boolean isSquare() {
    return (mwidth == mlength);
  }

  public int area() {
    return mlength * mwidth;
  }

}
