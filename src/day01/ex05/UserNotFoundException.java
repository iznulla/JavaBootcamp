package day01.ex05;

public class UserNotFoundException extends RuntimeException {

  UserNotFoundException(String message) {
    super(message);
  }
}
