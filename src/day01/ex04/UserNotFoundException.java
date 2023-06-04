package day01.ex04;

public class UserNotFoundException extends RuntimeException {

  UserNotFoundException(String message) {
    super(message);
  }
}
