public class Triangle {
  private int mSide1;
  private int mSide2;
  private int mSide3;

  public Triangle (int side1, int side2, int side3){
    mSide1 = side1;
    mSide2 = side2;
    mSide3 = side3;
  }

  public int getSide1(){
    return mSide1;
  }

  public int getSide2(){
    return mSide2;
  }

  public int getSide3(){
    return mSide3;
  }

  public String typeOfTriangle(){

    if ((mSide1 + mSide2) < mSide3 || (mSide2 + mSide3) <  mSide1 || (mSide1 + mSide3) <  mSide2){
      return "not a triangle";
    } else if (mSide1 == mSide2 && mSide2 == mSide3){
      return "equilateral";
    } else if (mSide1 == mSide2 || mSide2 == mSide3 ||  mSide3 == mSide1) {
      return "isosceles";
    } else {
      return "scalene";
    }
  }
}
