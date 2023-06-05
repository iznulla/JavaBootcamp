package day01.ex05;

import java.util.Scanner;
import java.util.UUID;

public class Program {

  static UserList userLst = new UsersArrayList();
  static TransactionsService trnService = new TransactionsService();
  public static void main(String[] args) {
    boolean flag = true;

    while (flag) {
      printStart();
      Scanner in = new Scanner(System.in);
      String str = in.next();
      if (str.equals("7"))
        flag = false;
      else if (str.equals("1")) {
        answerOne();
      } else if (str.equals("2")) {
        answerTwo();
      } else if (str.equals("3")) {
        answerThree();
      } else if (str.equals("4")) {
        answerFour();
      } else if (str.equals("5")) {
        answerFive();
      } else if (str.equals("6")) {
        answerSix();
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
  public static void answerOne() {
    System.out.println("Enter a user name and a balance");
    Scanner in_1 = new Scanner(System.in);
    User user = new User(in_1.next(), in_1.nextInt());
    trnService.addUser(user);
    userLst.addUser(user);
    System.out.println(user.getName() + " " + user.getBalance());
  }

  public static void answerTwo() {
    System.out.println("Enter a user ID");
    Scanner in_1 = new Scanner(System.in);
    Integer uId = in_1.nextInt();
    try {
      System.out.println(userLst.getUserById(uId).getName() + " - " + userLst.getUserById(uId).getBalance());
    }
    catch (UserNotFoundException e) {
      System.out.println(e.getMessage());
    }
  }

  public static void answerThree() {
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
  }

  public static void answerFour() {
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
  }

  public static void answerFive() {
    System.out.println("Enter a user ID and a transfer ID");
    Scanner in_1 = new Scanner(System.in);
    Integer usrId = in_1.nextInt();
    String transId = in_1.next();
    try {
      Transaction[] array = trnService.getTransactionsArray(usrId);
      for (Transaction t : array) {
        if (!t.getRecipient().getId().equals(usrId) && t.getIdentifier().equals(UUID.fromString(transId))) {
          System.out.println("Transfer To " + t.getRecipient().getName() + "(id = " +
              t.getRecipient().getId() + ") " + t.getAmount() + " removed");
        }
      }
      trnService.eraseTransaction(UUID.fromString(transId), usrId);
    }
    catch (IllegalArgumentException | IllegalTransactionException |
           UserNotFoundException | TransactionNotFoundException e) {
      System.out.println(e.getMessage());
    }
  }

  public static void answerSix() {
    try {
      System.out.println("Check results:");
      for (Transaction t : trnService.getInvalidTransactions()) {
        System.out.println(t.getRecipient().getName() + "(id = " +
            t.getRecipient().getId() + ") has an unacknowledged transfer id = " +
            t.getIdentifier()
            + " from " + t.getSender().getName() + "(id = " + t.getSender().getId() +
            " for " + t.getAmount());
      }
    }
    catch (IllegalArgumentException | IllegalTransactionException |
           UserNotFoundException | TransactionNotFoundException e) {
      System.out.println(e.getMessage());
    }
  }
}

