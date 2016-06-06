import org.junit.*;
import org.sql2o.*;
import java.util.List;
import static org.junit.Assert.*;

public class BookTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void Book_instantiatesCorrectly_true(){
    Book myBook = new Book("Tom Sawyer");
    assertEquals(true, myBook instanceof Book);
  }

  @Test
  public void getTitle_instantiatesWithTitle_true(){
    Book myBook = new Book("Tom Sawyer");
    assertEquals("Tom Sawyer", myBook.getTitle());
  }

  @Test
  public void all_emptyAtFirst() {
    assertEquals(Book.all().size(), 0);
  }

  @Test
  public void equals_returnsTrueIfTitleAreTheSame_true() {
    Book firstBook = new Book("Tom Sawyer");
    Book secondBook = new Book("Tom Sawyer");
    assertTrue(firstBook.equals(secondBook));
  }

  @Test
  public void save_savesObjectIntoDatabase_true() {
    Book myBook = new Book("Tom Sawyer");
    myBook.save();
    assertTrue(Book.all().get(0).equals(myBook));
  }

  @Test
  public void save_assignsIdToObject() {
    Book myBook = new Book("Tom Sawyer");
    myBook.save();
    Book savedBook = Book.all().get(0);
    assertEquals(myBook.getId(), savedBook.getId());
  }

  @Test
  public void find_findsBookInDatabase_True() {
    Book myBook = new Book("Tom Sawyer");
    myBook.save();
    Book savedBook = Book.find(myBook.getId());
    assertTrue(myBook.equals(savedBook));
  }

  @Test
  public void update_updatesBookTitle_true() {
    Book myBook = new Book("Tom Sawyer");
    myBook.save();
    myBook.update("Pitter Pen");
    assertEquals("Pitter Pen", Book.find(myBook.getId()).getTitle());
  }

  @Test
  public void delete_deletesBook_true() {
    Book myBook = new Book("Tom Sawyer");
    myBook.save();
    int myBookId = myBook.getId();
    myBook.delete();
    assertEquals(null, Book.find(myBookId));
  }

  @Test
  public void addBook_addsBookToAuthor() {
    Book myBook = new Book("Tom Sawyer");
    myBook.save();
    Author myAuthor = new Author("Dave", "Smith");
    myAuthor.save();
    myAuthor.addBook(myBook);
    Book savedBook = myAuthor.getBooks().get(0);
    assertTrue(myBook.equals(savedBook));
  }

  @Test
  public void getBooks_returnsAllBooks_List() {
    Book myBook = new Book("Tom Sawyer");
    myBook.save();
    Author myAuthor = new Author("Dave", "Smith");
    myAuthor.save();
    myAuthor.addBook(myBook);
    List savedBooks = myAuthor.getBooks();
    assertEquals(1, savedBooks.size());
  }

  @Test
  public void delete_deletesAllAuthorAndBookAssociations() {
    Book myBook = new Book("Tom Sawyer");
    myBook.save();
    Author myAuthor = new Author("Dave", "Smith");
    myAuthor.save();
    myAuthor.addBook(myBook);
    myAuthor.delete();
    assertEquals(0, myBook.getAuthors().size());
  }

  @Test
  public void addAuthor_addsAuthorToBook() {
    Author myAuthor = new Author("Dave", "Smith");
    myAuthor.save();
    Book myBook = new Book("Tom Sawyer");
    myBook.save();
    myBook.addAuthor(myAuthor);
    Author savedAuthor = myBook.getAuthors().get(0);
    assertTrue(myAuthor.equals(savedAuthor));
  }

  @Test
  public void getAuthors_returnsAllAuthors_List() {
    Author myAuthor = new Author("Dave", "Smith");
    myAuthor.save();
    Book myBook = new Book("Tom Sawyer");
    myBook.save();
    myBook.addAuthor(myAuthor);
    List savedAuthors = myBook.getAuthors();
    assertEquals(1, savedAuthors.size());
  }

  @Test
  public void delete_deletesAllBookAndAuthorAssociations() {
    Author myAuthor = new Author("Dave", "Smith");
    myAuthor.save();
    Book myBook = new Book("Tom Sawyer");
    myBook.save();
    myBook.addAuthor(myAuthor);
    myBook.delete();
    assertEquals(0, myBook.getAuthors().size());
  }

}
