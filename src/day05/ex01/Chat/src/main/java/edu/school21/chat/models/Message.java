package edu.school21.chat.models;

import edu.school21.chat.models.ChatRoom;
import java.util.Date;
import java.util.Objects;

public class Message {
  private final long ID;
  private final User author;
  private final ChatRoom room;
  private final String text;
  private final Date datetime;

  public Message(long id, User author, ChatRoom room, String text, Date datetime) {
    this.ID = id;
    this.author = author;
    this.room = room;
    this.text = text;
    this.datetime = datetime;
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) return true;
    if (o == null || o.getClass() != this.getClass())
      return false;
    Message message = (Message) o;
    return this.ID == message.ID &&
        Objects.equals(this.author, message.author) &&
        Objects.equals(this.room, message.room) &&
        Objects.equals(this.text, message.text) &&
        Objects.equals(this.datetime, message.datetime);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.ID, this.author, this.room, this.text, this.datetime);
  }

  @Override
  public String toString() {
    return "Message : {\n" +
        "id=" + this.ID +
        ",\nauthor={" + author +
        "},\nroom={" + room +
        "},\ntext=" + text +
        ",\ndatetime=" + datetime;
  }
}
