package edu.school21.chat.app;

import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;
import edu.school21.chat.repositories.HikDataSource;
import edu.school21.chat.repositories.MessagesRepository;
import edu.school21.chat.repositories.MessagesRepositoryJdbcImpl;
import java.sql.SQLException;
import java.util.Optional;

public class Program {

  public static void main(String[] args) throws SQLException {
    MessagesRepository msg = new MessagesRepositoryJdbcImpl(HikDataSource.getHkDataSource());
    Optional<Message> messageOptional = msg.findById(7L);
    if (messageOptional.isPresent()) {
      System.out.println(messageOptional.orElse(null));
      Message message = messageOptional.get();
      message.setText("Bella Ciao");
      message.setDatetime(null);
      msg.update(message);
    }
    messageOptional = msg.findById(7L);
    if (messageOptional.isPresent()) {
      System.out.println(messageOptional.orElse(null));
    }
  }
}
