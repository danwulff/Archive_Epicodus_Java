import org.junit.*;
import static org.junit.Assert.*;

public class ParcelTest {

  @Test
  public void newParcel_instatiatesCorrectly() {
    Parcel testParcel = new Parcel (2, 4, 2, 10);
    assertEquals(true, testParcel instanceof Parcel);
  }

  @Test
  public void getVolume_getsParcelVolume_8() {
    Parcel testParcel = new Parcel (2, 2, 2, 10);
    assertEquals(8, testParcel.getVolume());
  }

  @Test
  public void getCostToShip_getsCostVolumeSmall_10() {
    Parcel testParcel = new Parcel (2, 2, 2, 10);
    assertEquals(10, testParcel.getCostToShip());
  }

  @Test
  public void getCostToShip_getsCostVolumeMedium_20() {
    Parcel testParcel = new Parcel (2, 5, 4, 10);
    assertEquals(20, testParcel.getCostToShip());
  }

  @Test
  public void getCostToShip_getsCostVolumeLarge_50() {
    Parcel testParcel = new Parcel (50, 50, 2, 10);
    assertEquals(50, testParcel.getCostToShip());
  }
}
