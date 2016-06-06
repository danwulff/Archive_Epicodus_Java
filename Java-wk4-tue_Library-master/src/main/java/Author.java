import java.util.List;
import java.util.ArrayList;
import org.sql2o.*;

public class Author {
  private String first_name;
  private String last_name;
  private int id;

  public Author (String firstName, String lastName) {
    this.first_name = firstName;
    this.last_name = lastName;
  }

  public String getFirstName() {
    return this.first_name;
  }

  public String getLastName() {
    return this.last_name;
  }

  public int getId() {
    return this.id;
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO authors (first_name, last_name) VALUES (:first, :last)";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("first", this.first_name)
        .addParameter("last", this.last_name)
        .executeUpdate()
        .getKey();
    }
  }

  public void update(String newFirstName, String newLastName) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE authors SET first_name = :first, last_name = :last WHERE id = :id";
      con.createQuery(sql)
        .addParameter("id", this.id)
        .addParameter("first", newFirstName)
        .addParameter("last", newLastName)
        .executeUpdate();
    }
  }

  public void addBook(Book newBook) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO book_author (book_id, author_id) VALUES (:book, :author)";
      con.createQuery(sql)
        .addParameter("book", newBook.getId())
        .addParameter("author", this.id)
        .executeUpdate();
    }
  }

  public List<Book> getBooks() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT book_id FROM book_author WHERE author_id = :author_id";

      List<Integer> bookIds =  con.createQuery(sql)
        .addParameter("author_id", this.id)
        .executeAndFetch(Integer.class);

      List<Book> books = new ArrayList<Book>();

      for (Integer book_id : bookIds) {
        String bookQuery = "Select * FROM books WHERE id = :book_id";
        Book tempBook = con.createQuery(bookQuery)
          .addParameter("book_id", book_id)
          .executeAndFetchFirst(Book.class);
        books.add(tempBook);
      }
      return books;
    }
  }

  public void delete() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM authors WHERE id = :id";
      con.createQuery(sql)
        .addParameter("id", this.id)
        .executeUpdate();

      String joinTableDelete = "DELETE FROM book_author WHERE author_id = :authorId";
      con.createQuery(joinTableDelete)
        .addParameter("authorId", this.id)
        .executeUpdate();
    }
  }

  public static Author find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM authors WHERE id = :id";
      Author author = con.createQuery(sql)
              .addParameter("id", id)
              .executeAndFetchFirst(Author.class);
      return author;
    }
  }

  public static List<Author> all() {
    String sql = "SELECT id, first_name, last_name FROM authors";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Author.class);
    }
  }

  @Override
  public boolean equals(Object otherAuthor) {
    if (!(otherAuthor instanceof Author)) {
      return false;
    } else {
      Author newAuthor =  (Author) otherAuthor;
      return this.getFirstName().equals(newAuthor.getFirstName()) &&
             this.getLastName().equals(newAuthor.getLastName()) &&
             this.getId() == newAuthor.getId();
    }
  }
}
