package edu.school21.chat.app;

import edu.school21.chat.models.Message;
import edu.school21.chat.repositories.HikDataSource;
import edu.school21.chat.repositories.MessagesRepository;
import edu.school21.chat.repositories.MessagesRepositoryJdbcImpl;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Scanner;

public class Program {

  public static void main(String[] args) throws SQLException {
    System.out.println("Enter a message ID");
    Scanner in = new Scanner(System.in);
    while (in.hasNext()) {

      MessagesRepository msg = new MessagesRepositoryJdbcImpl(HikDataSource.getHkDataSource());
      try {
        String line = in.next();
        if (line.equals("exit"))
          break;
        Long id = Long.parseLong(line);
        Optional<Message> message = msg.findById(id);
        if (message.isPresent())
          System.out.println(message.orElse(null));
        else
          System.out.println("Message not found by id="+id);
      } catch (NumberFormatException e) {
        System.out.println("Illegal symbol");
      }

    }
  }
}
