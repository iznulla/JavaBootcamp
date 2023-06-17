package edu.school21.chat.repositories;

import com.zaxxer.hikari.HikariDataSource;
import edu.school21.chat.Message;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import javax.sql.DataSource;

public class MessagesRepositoryJdbcImpl implements MessagesRepository {

  DataSource connection;

  public MessagesRepositoryJdbcImpl(DataSource dataSource) {
    this.connection = dataSource;
  }

  @Override
  public Optional<Message> findById(Long id) throws SQLException {
    Connection connection1 = HikDataSource.getConnection();
    String sql = "SELECT * FROM public.messages WHERE author_id = ?";
    PreparedStatement statement = connection1.prepareStatement(sql);
    statement.setLong(1, id);
    ResultSet resultSet = statement.executeQuery();
    resultSet.next();
    System.out.println(resultSet.getString("text"));
    connection1.close();
    return Optional.empty();
  }
}
