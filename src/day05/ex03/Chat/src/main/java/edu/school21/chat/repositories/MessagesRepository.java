package edu.school21.chat.repositories;

import edu.school21.chat.models.Message;
import java.sql.SQLException;
import java.util.Optional;
import org.postgresql.jdbc.PgArray;

public interface MessagesRepository {
  Optional<Message> findById(Long id) throws SQLException;
  void save(Message m) throws SQLException;
  void update(Message m) throws SQLException;
}
