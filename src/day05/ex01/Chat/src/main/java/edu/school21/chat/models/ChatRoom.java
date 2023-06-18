package edu.school21.chat.models;


import java.sql.Array;
import java.util.Objects;

public class ChatRoom {
  private final long ID;
  private final String name;
  private final User owner;
  private final Array messages;

  public ChatRoom(long id, String name, User owner, Array messages ) {
    this.ID = id;
    this.name = name;
    this.owner = owner;
    this.messages = messages;
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) return true;
    if (o == null || this.getClass() != o.getClass())
      return false;
    ChatRoom room = (ChatRoom) o;
    return this.ID == room.ID &&
        (Objects.equals(this.name, room.name) &&
        Objects.equals(this.owner, room.owner) &&
        Objects.equals(this.messages, room.messages));
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.ID, this.name, this.owner, this.messages);
  }

  @Override
  public String toString() {
    return "id=" + this.ID +
        ",name=" + this.name +
        ",owner=" + this.owner.getLogin() +
        ",messages=" +this.messages;
  }
}
