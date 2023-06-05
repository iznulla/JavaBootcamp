package day01.ex05;

public interface UserList {

  void addUser(User value);

  public User getUserById(Integer value);

  public User getUserByIndex(int value);

  public int getUserNumber();
}
