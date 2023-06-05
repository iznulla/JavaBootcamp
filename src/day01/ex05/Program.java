package day01.ex05;

import java.util.Scanner;
import java.util.UUID;

public class Program {

  public static void main(String[] args) {
    boolean flag = true;
    UserList userLst = new UsersArrayList();
    TransactionsService trnService = new TransactionsService();
    while (flag) {
      printStart();
      Scanner in = new Scanner(System.in);
      String str = in.next();
      if (str.equals("7"))
        flag = false;
      else if (str.equals("1")) {
        System.out.println("Enter a user name and a balance");
        Scanner in_1 = new Scanner(System.in);
        User user = new User(in_1.next(), in_1.nextInt());
        trnService.addUser(user);
        userLst.addUser(user);
        System.out.println(user.getName() + " " + user.getBalance());
      } else if (str.equals("2")) {
        System.out.println("Enter a user ID");
        Scanner in_1 = new Scanner(System.in);
        Integer uId = in_1.nextInt();
        try {
          System.out.println(userLst.getUserById(uId).getName() + " - " + userLst.getUserById(uId).getBalance());
        }
        catch (UserNotFoundException e) {
          System.out.println(e.getMessage());
        }
      } else if (str.equals("3")) {
        System.out.println("Enter a sender ID, a recipient ID, and a transfer amount");
        Scanner in_1 = new Scanner(System.in);
        Integer recipientId = in_1.nextInt();
        Integer senderId = in_1.nextInt();
        Integer amount = in_1.nextInt();
        try {
          trnService.newTransaction(senderId, recipientId, amount);
          System.out.println("The transfer is complete");
        }
        catch (UserNotFoundException e) {
          System.out.println(e.getMessage());
        }

      } else if (str.equals("4")) {
        System.out.println("Enter a user ID");
        Scanner in_1 = new Scanner(System.in);
        Integer uId = in_1.nextInt();
        try {
          Transaction[] array = trnService.getTransactionsArray(uId);
          for (Transaction t : array) {
            if (!t.getRecipient().getId().equals(uId)) {
              System.out.println(
                  "To " + t.getRecipient().getName() + "(id = " + t.getRecipient().getId() + ") " +
                      t.getAmount() + " with id = " + t.getIdentifier());
            }
          }
        }
        catch (UserNotFoundException e) {
          System.out.println(e.getMessage());
        }
      } else if (str.equals("5")) {
        System.out.println("Enter a user ID and a transfer ID");
        Scanner in_1 = new Scanner(System.in);
        Integer usrId = in_1.nextInt();
        String transId = in_1.next();
        try {
          trnService.eraseTransaction(UUID.fromString(transId), usrId);
          Transaction[] array = trnService.getTransactionsArray(usrId);
          for (Transaction t : array) {
            if (!t.getRecipient().getId().equals(usrId)) {
              System.out.println("Transfer To " + t.getRecipient().getName() + "(id = " +
                  t.getRecipient().getId() + ") " + t.getAmount() + " removed");
            }
          }
        }
        catch (IllegalArgumentException | IllegalTransactionException | UserNotFoundException | TransactionNotFoundException e) {
          System.out.println(e.getMessage());
        }
      }
    }
  }
  public static void printStart() {
    System.out.println("1. Add a user\n"
        + "2. View user balances\n"
        + "3. Perform a transfer\n"
        + "4. View all transactions for a specific user\n"
        + "5. DEV – remove a transfer by ID\n"
        + "6. DEV – check transfer validity\n"
        + "7. Finish execution\n");
  }
}
//    User ayrat = new User("Ayrat", 2000);
//    User sasa = new User("Sasa", 3000);
//    User masa = new User("masa", 5000);
//
//    TransactionsService trService = new TransactionsService();
//
//    trService.addUser(ayrat);
//    trService.addUser(sasa);
//    trService.addUser(masa);
//
//    int ayrat_balance = trService.getBalanceById(ayrat.getId());
//    int sasa_balance = trService.getBalanceById(sasa.getId());
//    int masa_balance = trService.getBalanceById(masa.getId());
//
//    System.out.println("Ayrat balance before - " + ayrat_balance);
//    System.out.println("Sasa balance before - " + sasa_balance);
//    System.out.println("Masa balance after - " + masa_balance);
//
//    System.out.println("____________________________________________________________");
//
//    trService.newTransaction(ayrat.getId(), sasa.getId(), 60);
//    trService.newTransaction(sasa.getId(), ayrat.getId(), 125);
//    trService.newTransaction(sasa.getId(), masa.getId(), 230);
//    trService.newTransaction(masa.getId(), ayrat.getId(), 320);
//
//    ayrat_balance = trService.getBalanceById(ayrat.getId());
//    sasa_balance = trService.getBalanceById(sasa.getId());
//    masa_balance = trService.getBalanceById(masa.getId());
//
//    System.out.println("Ayrat balance after - " + ayrat_balance);
//    System.out.println("Sasa balance after - " + sasa_balance);
//    System.out.println("Masa balance after - " + masa_balance);
//
//    System.out.println("____________________________________________________________");
//
//    Transaction[] ayrat_trnsc = trService.getTransactionsArray(ayrat.getId());
//    for (Transaction t : ayrat_trnsc) {
//      System.out.println("Ayrat Transactions before remove - " + t.getAmount());
//    }
//
//    System.out.println("____________________________________________________________");
//
//    trService.eraseTransaction(ayrat_trnsc[1].getIdentifier(), ayrat.getId());
//
//    ayrat_trnsc = trService.getTransactionsArray(ayrat.getId());
//    for (Transaction t : ayrat_trnsc) {
//      System.out.println("Ayrat Transactions after remove - " + t.getAmount());
//    }
//  }
//}
