package edu.school21.chat;

import java.util.List;
import java.util.Objects;

public class User {
  private final long ID;
  private final String login;
  private final String password;
  List<ChatRoom> createdRooms;
  List<ChatRoom> userSocialized;

  public User(long id, String login, String password, List<ChatRoom> createdRooms,
  List<ChatRoom> userSocialized) {
    this.ID = id;
    this.login = login;
    this.password = password;
    this.createdRooms = createdRooms;
    this.userSocialized = userSocialized;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || this.getClass() != o.getClass())
      return false;
    User user = (User) o;
    return user.ID == this.ID &&
        ((user.login != null && this.login.equals(user.login)) &&
            (user.password != null && this.password.equals(user.login)) &&
            (user.createdRooms != null && this.createdRooms.equals(user.createdRooms)) &&
            (user.userSocialized != null && this.userSocialized.equals(user.userSocialized)));
  }

  @Override
  public int hashCode() {
    return Objects.hash(ID, login, password, createdRooms, userSocialized);
  }

  @Override
  public String toString() {
    return "User {"
        + "ID = " + this.ID
        + ", Login = " + login +
        ", Password = " + password +
        ", CreatedRooms = " + createdRooms +
        ", UserSocializes = " + userSocialized;
  }

}
