package day01.ex03;


import java.util.UUID;

public interface TransactionsList {
  void addTransaction(Transaction value);
  void eraseTransactionById(UUID value);
  Transaction[] toArray();
}
