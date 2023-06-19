package edu.school21.chat.models;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

public class Message {
  private Long ID;
  private final User author;
  private final ChatRoom room;
  private final String text;
  private final Timestamp datetime;

  public Message(Long id, User author, ChatRoom room, String text, Timestamp datetime) {
    this.ID = id;
    this.author = author;
    this.room = room;
    this.text = text;
    this.datetime = datetime;
  }

  public Long getID() {
    return ID;
  }
  public User getAuthor() {
    return author;
  }

  public ChatRoom getRoom() {
    return room;
  }

  public String getText() {
    return text;
  }

  public Date getDatetime() {
    return datetime;
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
        ",\ndatetime=" + datetime
        + "}";
  }

  public void setId(long generatedId) {
    ID = generatedId;
  }
}
