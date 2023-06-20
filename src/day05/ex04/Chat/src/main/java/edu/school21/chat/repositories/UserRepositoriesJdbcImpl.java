package edu.school21.chat.repositories;

import edu.school21.chat.models.ChatRoom;
import edu.school21.chat.models.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

public class UserRepositoriesJdbcImpl implements UserRepositories {

  Connection con;

  public UserRepositoriesJdbcImpl(DataSource dataSource) throws SQLException {
    this.con = dataSource.getConnection();
  }

  @Override
  public List<User> findAll(int page, int size) {
    ArrayList<User> users = new ArrayList<>();
    String sql = "WITH CTE AS (\n"
        + "\tSELECT users.id, users.login, users.password, room.id as room_id, room.chatowner_id, room.chatname, \n"
        + "\tmessage.id as mess_id, message.text\n"
        + "\tFROM users\n"
        + "\tJOIN public.chatroom room on users.id = room.chatowner_id\n"
        + "\tJOIN messages message on users.id = message.author_id\n"
        + ")"
        + "SELECT * FROM CTE OFFSET ? LIMIT ?;";
    try (PreparedStatement statement = con.prepareStatement(sql)) {
      statement.setInt(1, (page - 1) * size);
      statement.setInt(2, size);
      ResultSet resultSet = statement.executeQuery();

      while (resultSet.next()) {
        User user = new User(
            resultSet.getLong("id"),
            resultSet.getString("login"),
            resultSet.getString("password"),
            new ArrayList<>(),
            new ArrayList<>());
        if (!users.contains(user)) {
          users.add(user);
        }
        user = users.get(users.size() - 1);
        long owner_chat_id = resultSet.getLong("chatowner_id");
        if (owner_chat_id != 0 &&
            user.crRoomsList().stream().noneMatch(chatRoom -> chatRoom.getId() == owner_chat_id)) {
          ChatRoom chatRoom = new ChatRoom(resultSet.getLong("room_id"),
              resultSet.getString("chatname"), user, null);
          user.crRoomsList().add(chatRoom);
        }
        long socializes_chat = resultSet.getLong("mess_id");
        if (socializes_chat != 0 &&
            user.socializeeRooms().stream()
                .noneMatch(chatRoom -> chatRoom.getId() == socializes_chat)) {
          ChatRoom socializesChat = new ChatRoom(
              resultSet.getLong("mess_id"),
              resultSet.getString("chatname"),
              user,
              null);
          user.socializeeRooms().add(socializesChat);

        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return users;
  }
}
