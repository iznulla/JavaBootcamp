package day01.ex04;

public class TransactionNotFoundException extends RuntimeException {
  TransactionNotFoundException(String message) {
    super(message);
  }
}
