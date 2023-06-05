package day01.ex05;


import java.util.UUID;

public interface TransactionsList {
  void addTransaction(Transaction value);
  void eraseTransactionById(UUID value);
  Transaction[] toArray();
}
