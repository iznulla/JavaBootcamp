package day01.ex05;

public class TransactionNotFoundException extends RuntimeException {
  TransactionNotFoundException(String message) {
    super(message);
  }
}
