package day01.ex03;


public class User {

  private final Integer id;
  private String name;
  private Integer balance;

  private TransactionsList transactions;

  User(String name, int balance) {
    this.id = UserIdsGenerator.getInstance().generateId();
    this.name = name;
    this.balance = balance;
    this.transactions = new TransactionsLinkedList();
  }

  public Integer getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public Integer getBalance() {
    return balance;
  }

  public void setName(String value) {
    this.name = value;
  }

  public void setBalance(Integer value) {
    this.balance = value;
  }

  public TransactionsList geTransactionsList() {
    return transactions;
  }

}


