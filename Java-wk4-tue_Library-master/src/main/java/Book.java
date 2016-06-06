import java.util.List;
import java.util.ArrayList;
import org.sql2o.*;


public class Book {
  private String title;
  private int id;

  public Book(String title) {
    this.title = title;
  }

  public String getTitle(){
    return title;
  }

  public int getId(){
    return id;
  }

  public void save(){
    try(Connection con = DB.sql2o.open()){
      String sql = "INSERT INTO books (title) VALUES (:title)";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("title", this.title)
        .executeUpdate()
        .getKey();
    }
  }

  public void update(String newTitle) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE books SET title = :title WHERE id = :id";
      con.createQuery(sql)
        .addParameter("id", this.id)
        .addParameter("title", newTitle)
        .executeUpdate();
    }
  }

  public void delete(){
    try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM books WHERE id = :id";
      con.createQuery(sql)
        .addParameter("id", this.id)
        .executeUpdate();

      String joinTableDelete = "DELETE FROM book_author WHERE book_id = :bookId";
      con.createQuery(joinTableDelete)
        .addParameter("bookId", this.id)
        .executeUpdate();
    }
  }

  public void addAuthor(Author newAuthor) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO book_author (book_id, author_id) VALUES (:book, :author)";
      con.createQuery(sql)
        .addParameter("book", this.id)
        .addParameter("author", newAuthor.getId())
        .executeUpdate();
    }
  }

  public List<Author> getAuthors() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT author_id FROM book_author WHERE book_id = :book_id";

      List<Integer> authorIds =  con.createQuery(sql)
        .addParameter("book_id", this.id)
        .executeAndFetch(Integer.class);

      List<Author> authors = new ArrayList<Author>();

      for (Integer author_id : authorIds) {
        String authorQuery = "Select * FROM authors WHERE id = :author_id";
        Author tempAuthor = con.createQuery(authorQuery)
          .addParameter("author_id", author_id)
          .executeAndFetchFirst(Author.class);
        authors.add(tempAuthor);
      }
      return authors;
    }
  }

  public static Book find(int id){
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM books WHERE id = :id";
      Book book = con.createQuery(sql)
          .addParameter("id", id)
          .executeAndFetchFirst(Book.class);
      return book;
    }
  }


  @Override
  public boolean equals(Object otherBook) {
    if (!(otherBook instanceof Book)) {
      return false;
    } else {
      Book newBook =  (Book) otherBook;
      return this.getTitle().equals(newBook.getTitle()) &&
             this.getId() == newBook.getId();
    }
  }

  public static List<Book> all(){
   String sql = "SELECT id, title FROM books";
   try(Connection con = DB.sql2o.open()) {
     return con.createQuery(sql).executeAndFetch(Book.class);
   }
  }
}
