package day01.ex03;



public class Program {

  public static void main(String[] args) {
    User ayrat = new User("Ayrat", 2000);
    User sasa = new User( "Sasa", 3000);

    System.out.println("ID - " + ayrat.getId() +
        ", name - " + ayrat.getName() +
        ", balance - " + ayrat.getBalance());

    System.out.println("ID - " + sasa.getId() +
        ", name - " + sasa.getName() +
        ", balance - " + sasa.getBalance());

    Transaction tr_ayrat_to_sasa = new Transaction(ayrat, sasa, TransCategory.credits, -60);
    Transaction tr_sasa_to_ayrat = new Transaction(sasa, ayrat, TransCategory.debits, 90);
    Transaction tr_1 = new Transaction(sasa, ayrat, TransCategory.debits, 30);

    System.out.println("ID - " + tr_ayrat_to_sasa.getIdentifier() +
        ", Recipient - " + tr_ayrat_to_sasa.getRecipient().getName() +
        ", Sender - " + tr_ayrat_to_sasa.getSender().getName() +
        ", Transfer category - " + tr_ayrat_to_sasa.getTransCategory() +
        ", Transfer amount - " + tr_ayrat_to_sasa.getAmount());

    System.out.println("ID - " + tr_sasa_to_ayrat.getIdentifier() +
        ", Recipient - " + tr_sasa_to_ayrat.getRecipient().getName() +
        ", Sender - " + tr_sasa_to_ayrat.getSender().getName() +
        ", Transfer category - " + tr_sasa_to_ayrat.getTransCategory() +
        ", Transfer amount - " + tr_sasa_to_ayrat.getAmount());

    System.out.println("ID - " + tr_1.getIdentifier() +
        ", Recipient - " + tr_1.getRecipient().getName() +
        ", Sender - " + tr_1.getSender().getName() +
        ", Transfer category - " + tr_1.getTransCategory() +
        ", Transfer amount - " + tr_1.getAmount());

    TransactionsList lst = new TransactionsLinkedList();
    lst.addTransaction(tr_ayrat_to_sasa);
    lst.addTransaction(tr_sasa_to_ayrat);
    lst.addTransaction(tr_1);

    Transaction[] transactionsArray = lst.toArray();
//
    for (Transaction t: transactionsArray) {
      System.out.println(t.getSender().getName());
    }

    lst.eraseTransactionById(tr_1.getIdentifier());
//
    Transaction[] transactionsArray_2 = lst.toArray();

    lst.eraseTransactionById(tr_1.getIdentifier());

    for (Transaction t: transactionsArray_2) {
      System.out.println(t.getIdentifier());
    }
  }



}
