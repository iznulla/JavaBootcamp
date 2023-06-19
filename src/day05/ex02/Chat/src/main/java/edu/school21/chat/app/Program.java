package edu.school21.chat.app;


import edu.school21.chat.models.ChatRoom;
import edu.school21.chat.models.Message;

import edu.school21.chat.models.User;
import edu.school21.chat.repositories.HikDataSource;
import edu.school21.chat.repositories.MessagesRepository;
import edu.school21.chat.repositories.MessagesRepositoryJdbcImpl;

import java.sql.SQLException;
;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Program {

  public static void main(String[] args) throws SQLException {

    User creator = new User(7L, "Ayzat", "cornhub17", new ArrayList<>(), new ArrayList<>());
    User author = creator;
    ChatRoom room = new ChatRoom(8L, "room", creator, new ArrayList<>());
    Message message = new Message(null, author, room, "Hello!",
        Timestamp.valueOf(LocalDateTime.now()));
    MessagesRepository msg = new MessagesRepositoryJdbcImpl(HikDataSource.getHkDataSource());
    msg.save(message);

  }
}
