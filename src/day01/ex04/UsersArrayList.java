package day01.ex04;


public class UsersArrayList implements UserList {

  private User[] userArray;
  private int capacity = 10;
  private int userNumber = 0;

  UsersArrayList() {
    userArray = new User[capacity];
  }

  @Override
  public void addUser(User value) {
    if (userNumber == capacity) {
      this.reserve();
    }
    userArray[this.userNumber] = value;
    ++userNumber;
  }

  @Override
  public User getUserById(Integer value) {
    User userFound = null;
    for (int i = 0; i < this.userNumber; ++i) {
      if (this.userArray[i].getId().equals(value)) {
        userFound = this.userArray[i];
      }
    }
    if (userFound == null) {
      throw new UserNotFoundException("User not found");
    }
    return userFound;
  }

  @Override
  public User getUserByIndex(int value) {
    if (value >= this.userNumber || value < 0) {
      throw new UserNotFoundException("User not found");
    } else {
      return this.userArray[value];
    }
  }

  @Override
  public int getUserNumber() {
    return this.userNumber;
  }

  private void reserve() {
    this.capacity += 10;
    User[] temp = new User[this.capacity];
    for (int i = 0; i < userNumber; ++i) {
      temp[i] = userArray[i];
    }
    userArray = temp;
  }
}
