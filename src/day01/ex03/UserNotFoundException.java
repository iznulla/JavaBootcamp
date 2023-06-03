package day01.ex03;

public class UserNotFoundException extends RuntimeException {

  UserNotFoundException(String message) {
    super(message);
  }
}
