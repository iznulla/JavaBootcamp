package day01.ex00;

public class User {

  User(Integer identifier, String name, int balance) {
    this.identifier = identifier;
    this.name = name;
    this.balance = balance;
  }

  private final
  Integer identifier;
    String name;
    Integer balance;

  public Integer getIdentifier() {
      return identifier;
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
