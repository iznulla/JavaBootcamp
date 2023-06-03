package day01.ex01;

public class Program {

  public static void main(String[] args) {
    User ayrat = new User("Ayrat", 2000);
    User sasa = new User("Sasa", 3000);

    System.out.println("ID - " + ayrat.getIdentifier() +
        ", name - " + ayrat.getName() +
        ", balance - " + ayrat.getBalance());

    System.out.println("ID - " + sasa.getIdentifier() +
        ", name - " + sasa.getName() +
        ", balance - " + sasa.getBalance());
  }
}
