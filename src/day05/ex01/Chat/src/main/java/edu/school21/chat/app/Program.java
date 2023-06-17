package edu.school21.chat.app;

import edu.school21.chat.repositories.HikDataSource;
import edu.school21.chat.repositories.MessagesRepository;
import edu.school21.chat.repositories.MessagesRepositoryJdbcImpl;
import java.sql.SQLException;

public class Program {

  public static void main(String[] args) throws SQLException {
//    Connection connection = DriverManager.getConnection(
//        "jdbc:postgresql://localhost:5432/chatdb",
//        "postgres", "1"
//    );
//
//    Statement statement = connection.createStatement();
//    ResultSet results = statement.executeQuery("SELECT * FROM users");
//
//    ResultSetMetaData resultSetMetaData = results.getMetaData();
//    String res = resultSetMetaData.getSchemaName(0);
//    System.out.println(res);
//    while (results.next()) {
//      int id = results.getInt(1);
//      String name = results.getString(2);
//      System.out.println(results.getRow() + ". " + id + "\t" + name);
//    }
//    connection.close();

//    DataSource dataSource;
//    Long e = Long.valueOf(2);
    MessagesRepository msg = new MessagesRepositoryJdbcImpl(HikDataSource.getHkDataSource());
    msg.findById(2L);
  }
}
