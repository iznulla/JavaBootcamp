package day01.ex05;

public class IllegalTransactionException extends RuntimeException{
  IllegalTransactionException(String message) {
    super(message);
  }
}
