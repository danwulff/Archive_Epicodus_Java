import org.junit.*;
import org.sql2o.*;
import java.util.List;
import static org.junit.Assert.*;

public class CopyTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void Copy_instantiatesCorrectly_true(){
    Copy myCopy = new Copy("available", 1);
    assertEquals(true, myCopy instanceof Copy);
  }

  @Test
  public void Copy_instantiatesWithStatus_String(){
    Copy myCopy = new Copy("available", 1);
    assertEquals("available", myCopy.getStatus());
  }

  @Test
  public void Copy_instantiatesWithBookId_Int(){
    Copy myCopy = new Copy("available", 1);
    assertEquals(1, myCopy.getBookId());
  }

  @Test
  public void save_assignsIdToObject() {
    Copy myCopy = new Copy("available", 1);
    myCopy.save();
    Copy savedCopy = Copy.all().get(0);
    assertEquals(myCopy.getId(), savedCopy.getId());
  }

  @Test
  public void find_findsCopyInDatabase_True() {
    Copy myCopy = new Copy("available", 1);
    myCopy.save();
    Copy saveCopy = Copy.find(myCopy.getId());
    assertTrue(myCopy.equals(saveCopy));
  }

}
