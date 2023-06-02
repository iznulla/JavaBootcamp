package day01.ex00;

public class Program {

  public static void main(String[] args) {
    User ayrat = new User(1234, "Ayrat", 2000 );
    User sasa = new User(2345, "Sasa", 3000);

    System.out.println("ID - " + ayrat.getIdentifier() +
        ", name - " + ayrat.getName() +
        ", balance - " + ayrat.getBalance());

    System.out.println("ID - " + sasa.getIdentifier() +
        ", name - " + sasa.getName() +
        ", balance - " + sasa.getBalance());

    Transaction tr_ayrat_to_sasa = new Transaction(ayrat, sasa, TransCategory.credits, -60);

    System.out.println("ID - " + tr_ayrat_to_sasa.getIdentifier() +
        ", Recipient - " + tr_ayrat_to_sasa.getRecipient().getName() +
        ", Sender - " + tr_ayrat_to_sasa.getSender().getName() +
        ", Transfer category - " + tr_ayrat_to_sasa.getTransCategory() +
        ", Transfer amount - " + tr_ayrat_to_sasa.getAmount());
  }
}
