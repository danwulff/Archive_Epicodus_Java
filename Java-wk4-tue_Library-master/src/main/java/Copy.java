import java.util.List;
import java.util.ArrayList;
import org.sql2o.*;


public class Copy {
  private String status;  //status = "avaiable" or "not-available"
  private int book_id;
  private int id;

  public Copy(String status, int bookId) {
    this.status = status;
    this.book_id = bookId;

  }

  public String getStatus() {
    return this.status;
  }

  public int getBookId() {
    return book_id;
  }

  public int getId(){
    return id;
  }

  public void save(){
    try(Connection con = DB.sql2o.open()){
      String sql = "INSERT INTO copies (status, book_id) VALUES (:status, :book_id)";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("status", this.status)
        .addParameter("book_id", this.book_id)
        .executeUpdate()
        .getKey();
    }
  }

  public static Copy find(int id){
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM copies WHERE id = :id";
      Copy copy = con.createQuery(sql)
          .addParameter("id", id)
          .executeAndFetchFirst(Copy.class);
      return copy;
    }
  }

  @Override
  public boolean equals(Object otherCopy) {
    if (!(otherCopy instanceof Copy)) {
      return false;
    } else {
      Copy newCopy =  (Copy) otherCopy;
      return this.getStatus().equals(newCopy.getStatus()) &&
             this.getBookId() == newCopy.getBookId() &&
             this.getId() == newCopy.getId();
    }
  }

  public static List<Copy> all(){
   String sql = "SELECT id, status FROM copies";
   try(Connection con = DB.sql2o.open()) {
     return con.createQuery(sql).executeAndFetch(Copy.class);
   }
  }

}
