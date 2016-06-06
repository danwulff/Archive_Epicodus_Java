public class Parcel {
  int mVolume;
  int mWeight;

  public Parcel(int length, int width, int height, int weight) {
    mVolume = length * width * height;
    mWeight = weight;
  }

  public int getVolume() {
    return mVolume;
  }

  public int getCostToShip() {
    int cost = 0;

    if (mVolume < 20) {
      cost = mWeight * 1;
    }
    else if (mVolume < 80 && mVolume >= 20) {
      cost = mWeight * 2;
    }
    else {
      cost = mWeight * 5;
    }

    return cost;
  }

}
