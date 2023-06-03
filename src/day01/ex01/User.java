package day01.ex01;

public class User {
  private final Integer id;
  private String name;
  private Integer balance;

  public User(String name, int balance) {
    this.id = UserIdsGenerator.getInstance().generateId();
    this.name = name;
    this.balance = balance;
  }

  public Integer getIdentifier() {
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
}

