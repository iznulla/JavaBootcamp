package day01.ex04;

public class IllegalTransactionException extends RuntimeException{
  IllegalTransactionException(String message) {
    super(message);
  }
}
