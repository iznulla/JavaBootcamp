package day01.ex04;

public class Program {

  public static void main(String[] args) {
    User ayrat = new User("Ayrat", 2000);
    User sasa = new User("Sasa", 3000);
    User masa = new User("masa", 5000);

    TransactionsService trService = new TransactionsService();

    trService.addUser(ayrat);
    trService.addUser(sasa);
    trService.addUser(masa);

    int ayrat_balance = trService.getBalanceById(ayrat.getId());
    int sasa_balance = trService.getBalanceById(sasa.getId());
    int masa_balance = trService.getBalanceById(masa.getId());

    System.out.println("Ayrat balance before - " + ayrat_balance);
    System.out.println("Sasa balance before - " + sasa_balance);
    System.out.println("Masa balance after - " + masa_balance);

    System.out.println("____________________________________________________________");

    trService.newTransaction(ayrat.getId(), sasa.getId(), 60);
    trService.newTransaction(sasa.getId(), ayrat.getId(), 125);
    trService.newTransaction(sasa.getId(), masa.getId(), 230);
    trService.newTransaction(masa.getId(), ayrat.getId(), 320);

    ayrat_balance = trService.getBalanceById(ayrat.getId());
    sasa_balance = trService.getBalanceById(sasa.getId());
    masa_balance = trService.getBalanceById(masa.getId());

    System.out.println("Ayrat balance after - " + ayrat_balance);
    System.out.println("Sasa balance after - " + sasa_balance);
    System.out.println("Masa balance after - " + masa_balance);

    System.out.println("____________________________________________________________");

    Transaction[] ayrat_trnsc = trService.getTransactionsArray(ayrat.getId());
    for (Transaction t : ayrat_trnsc) {
      System.out.println("Ayrat Transactions before remove - " + t.getAmount());
    }

    System.out.println("____________________________________________________________");

    trService.eraseTransaction(ayrat_trnsc[1].getIdentifier(), ayrat.getId());

    ayrat_trnsc = trService.getTransactionsArray(ayrat.getId());
    for (Transaction t : ayrat_trnsc) {
      System.out.println("Ayrat Transactions after remove - " + t.getAmount());
    }
  }
}
