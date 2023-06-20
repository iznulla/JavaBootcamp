package edu.school21.chat.repositories;


import edu.school21.chat.models.ChatRoom;
import edu.school21.chat.models.User;
import edu.school21.chat.models.Message;
import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
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
  public Optional<Message> findById(Long id) {
    String sql = "SELECT * FROM public.messages WHERE id = ?";
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
    }
    return Optional.empty();
  }

  @Override
  public void save(Message message) throws SQLException {
    String sql = "INSERT INTO public.messages (author_id, room_id, text, date) VALUES (?, ?, ?, ?)";
    checkParams(message);
    User user = findByUserId(message.getAuthor().getId()).orElse(null);
    ChatRoom chatRoom = findByChatId(message.getRoom().getId()).orElse(null);
    try (PreparedStatement statement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
      if (user != null && chatRoom != null) {
        statement.setLong(1, message.getAuthor().getId());
        statement.setLong(2, message.getRoom().getId());
        statement.setString(3, message.getText());
        statement.setTimestamp(4, (Timestamp) message.getDatetime());
        int rowsInserted = statement.executeUpdate();
        if (rowsInserted > 0) {
          ResultSet getGeneratedId = statement.getGeneratedKeys();
          if (getGeneratedId.next())
            message.setId(getGeneratedId.getLong(1));
          System.out.println(
              "A new message was inserted successfully!");
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

  @Override
  public void update(Message message) throws SQLException {
    String sql = "UPDATE public.messages SET author_id=?, room_id=?, text=?, date=? WHERE id=?";
    checkParams(message);

    if (message.getText() == null)
      message.setText("");

    if (message.getDatetime() == null) {
      message.setDatetime(Timestamp.valueOf(LocalDateTime.now()));
    }
    try (PreparedStatement statement = con.prepareStatement(sql)) {
      statement.setLong(1, message.getAuthor().getId());
      statement.setLong(2, message.getRoom().getId());
      statement.setString(3, message.getText());
      statement.setTimestamp(4, (Timestamp) message.getDatetime());
      statement.setLong(5, message.getID());
      int rowsUpdated = statement.executeUpdate();
      if (rowsUpdated > 0) {
        System.out.println("An existing message was updated successfully!");
      }
    } catch (SQLException e) {
      e.printStackTrace();
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
        return Optional.of(new User(id, login, passwd, null, null));
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

  private void checkParams(Message message) throws SQLException {

    if (message.getAuthor() == null) {
      throw new NotSavedSubEntityException("Author is null");
    }
    if (message.getRoom() == null) {
      throw new NotSavedSubEntityException("ChatRoom is null");
    }

    if (!findByUserId(message.getAuthor().getId()).isPresent()) {
      throw new NotSavedSubEntityException("User not found");
    }
    if (!findByChatId(message.getRoom().getId()).isPresent()) {
      throw new NotSavedSubEntityException("ChatRoom not found");
    }

  }
}
