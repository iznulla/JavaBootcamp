package edu.school21.chat.repositories;


import edu.school21.chat.models.ChatRoom;
import edu.school21.chat.models.User;
import edu.school21.chat.models.Message;
import java.awt.List;
import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Optional;
import javax.sql.DataSource;

public class MessagesRepositoryJdbcImpl implements MessagesRepository {
  Connection con;

  public MessagesRepositoryJdbcImpl(DataSource dataSource) throws SQLException {

    this.con = dataSource.getConnection();

  }

  @Override
  public Optional<Message> findById(Long id) throws SQLException {
    String sql = "SELECT * FROM public.messages WHERE author_id = ?";
    try {
      PreparedStatement statement = con.prepareStatement(sql);
      statement.setLong(1, id);
      ResultSet resultSet = statement.executeQuery();
      resultSet.next();
      Long user_id = resultSet.getLong("author_id");
      Long ms_room = resultSet.getLong("room_id");
      String msg = resultSet.getString("text");
      Timestamp tms = resultSet.getTimestamp("date");
      User author = findByUserId(user_id).orElse(null);
      ChatRoom chat = findByChatId(ms_room).orElse(null);
      return Optional.of(new Message(id, author, chat, msg, tms));
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      con.close();
    }

    return Optional.empty();
  }


  public Optional<User> findByUserId(Long id) throws SQLException {
    String sql = "SELECT * FROM public.Users WHERE id = ?";
    try {
      PreparedStatement statement = con.prepareStatement(sql);
      statement.setLong(1, id);
      ResultSet resultSet = statement.executeQuery();
      resultSet.next();;
      String login = resultSet.getString(2);
      String passwd = resultSet.getString(3);
//      Array crRooms = resultSet.getArray(4);
//      String[] nullable = (Arrays)crRooms.getArray();
      return Optional.of(new User(id, login, passwd, null, null));
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return Optional.empty();
  }

  public Optional<ChatRoom> findByChatId(Long id) {
    String sql = "SELECT * FROM public.chatroom WHERE id = ?";
    try {
      PreparedStatement statement = con.prepareStatement(sql);
      statement.setLong(1, id);
      ResultSet resultSet = statement.executeQuery();
      resultSet.next();
      String chname = resultSet.getString("chatname");
      Long chatOwnId = resultSet.getLong("chatowner_id");
      User own = findByUserId(chatOwnId).orElse(null);
      return Optional.of(new ChatRoom(id, chname, own, null));
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return Optional.empty();
  }
}
