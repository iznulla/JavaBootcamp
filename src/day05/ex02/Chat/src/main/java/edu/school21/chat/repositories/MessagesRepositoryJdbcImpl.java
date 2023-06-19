package edu.school21.chat.repositories;


import edu.school21.chat.models.ChatRoom;
import edu.school21.chat.models.User;
import edu.school21.chat.models.Message;
import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;
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
    try (PreparedStatement statement = con.prepareStatement(sql)) {
      statement.setLong(1, id);
      ResultSet resultSet = statement.executeQuery();
      if (resultSet.next()) {
        Long user_id = resultSet.getLong("author_id");
        Long ms_room = resultSet.getLong("room_id");
        String msg = resultSet.getString("text");
        Timestamp tms = resultSet.getTimestamp("date");
        User author = findByUserId(user_id).orElse(null);
        ChatRoom chat = findByChatId(ms_room).orElse(null);
        return Optional.of(new Message(id, author, chat, msg, tms));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      con.close();
    }
    return Optional.empty();
  }

  @Override
  public void save(Message message) throws SQLException {
    String sql = "INSERT INTO public.messages (author_id, room_id, text, date) VALUES (?, ?, ?, ?)";
    if (message.getAuthor() == null || message.getRoom() == null)
      throw new NotSavedSubEntityException("author or room is null");
    User user = findByUserId(message.getAuthor().getId()).orElse(null);
    ChatRoom chatRoom = findByChatId(message.getRoom().getId()).orElse(null);
    try (PreparedStatement statement = con.prepareStatement(sql)) {
      if (user != null && chatRoom != null) {
        statement.setLong(1, message.getAuthor().getId());
        statement.setLong(2, message.getRoom().getId());
        statement.setString(3, message.getText());
        statement.setTimestamp(4, (Timestamp) message.getDatetime());
        int rowsInserted = statement.executeUpdate();
        if (rowsInserted > 0) {
            System.out.println("A new user was inserted successfully!");
        }
      } else {
        throw new NotSavedSubEntityException("author or room is null");
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      con.close();
    }
  }


  public Optional<User> findByUserId(Long id) throws SQLException {
    String sql = "SELECT * FROM public.Users WHERE id = ?";
    try (PreparedStatement statement = con.prepareStatement(sql)) {
      statement.setLong(1, id);
      ResultSet resultSet = statement.executeQuery();
      if (resultSet.next()) {
        String login = resultSet.getString(2);
        String passwd = resultSet.getString(3);
        Array room = resultSet.getArray(4);
        Array usrSoc = resultSet.getArray(5);
        List<Object> crRooms = Collections.singletonList(room);
        List<Object> userSoc = Collections.singletonList(usrSoc);
        return Optional.of(new User(id, login, passwd, crRooms, userSoc));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return Optional.empty();
  }

  public Optional<ChatRoom> findByChatId(Long id) {
    String sql = "SELECT * FROM public.chatroom WHERE id = ?";
    try (PreparedStatement statement = con.prepareStatement(sql)) {
      statement.setLong(1, id);
      ResultSet resultSet = statement.executeQuery();
      if (resultSet.next()) {
        String chname = resultSet.getString("chatname");
        Long chatOwnId = resultSet.getLong("chatowner_id");
        User own = findByUserId(chatOwnId).orElse(null);
        return Optional.of(new ChatRoom(id, chname, own, null));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return Optional.empty();
  }
}
