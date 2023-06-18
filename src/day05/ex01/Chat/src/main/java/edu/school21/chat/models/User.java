package edu.school21.chat.models;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;


public class User {
  private final long ID;
  private final String login;
  private final String password;
  private final Arrays createdRooms;
  private final Arrays userSocialized;

  public User(long id, String login, String password, Arrays createdRooms,
  Arrays userSocialized) {
    this.ID = id;
    this.login = login;
    this.password = password;
    this.createdRooms = createdRooms;
    this.userSocialized = userSocialized;
  }

  public String getLogin() {
    return login;
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
    return "id=" + this.ID
        + ",login=" + login + ",password=" + password +
        ",createdRooms=" + createdRooms +
        ",userSocializes=" + userSocialized;
  }

}
