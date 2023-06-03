package day01.ex03;

public class TransactionNotFoundException extends RuntimeException {
  TransactionNotFoundException(String message) {
    super(message);
  }
}
