package edu.school21.chat.app;

import edu.school21.chat.repositories.HikDataSource;
import edu.school21.chat.repositories.MessagesRepository;
import edu.school21.chat.repositories.MessagesRepositoryJdbcImpl;
import java.sql.SQLException;

public class Program {

  public static void main(String[] args) throws SQLException {

    MessagesRepository msg = new MessagesRepositoryJdbcImpl(HikDataSource.getHkDataSource());
    System.out.println(msg.findById(4L).orElse(null));

  }
}
