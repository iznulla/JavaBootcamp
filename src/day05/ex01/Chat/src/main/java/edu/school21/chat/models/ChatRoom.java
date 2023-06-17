package edu.school21.chat;

import java.util.List;
import java.util.Objects;

public class ChatRoom {
  private final long ID;
  private final String name;
  private final User owner;
  List<Message> chatroom;

  public ChatRoom(long id, String name, User owner, List<Message> chatroom ) {
    this.ID = id;
    this.name = name;
    this.owner = owner;
    this.chatroom = chatroom;
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
        Objects.equals(this.chatroom, room.chatroom));
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.ID, this.name, this.owner, this.chatroom);
  }

  @Override
  public String toString() {
    return "ChatRoom {" +
        "ID = " + this.ID +
        ", name = " + this.name +
        ", owner = " + this.owner +
        ", chatroom = " +this.chatroom;
  }
}
