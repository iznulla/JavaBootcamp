package day01.ex04;


import java.util.UUID;

public class TransactionsService {

  private final UserList userlst = new UsersArrayList();

  public void addUser(User value) {
    userlst.addUser(value);
  }

  public Integer getBalanceById(Integer userId) {
    return userlst.getUserById(userId).getBalance();
  }

  public void newTransaction(Integer recipient, Integer sender, Integer amount) {

    User recip = userlst.getUserById(recipient);
    User send = userlst.getUserById(sender);

    if ((send.getBalance() < amount) || (send == recip) || amount < 0) {
      throw new IllegalTransactionException("Invalid Transaction");
    }
    Transaction transactDeb = new Transaction(recip, send, TransCategory.debits, amount);
    Transaction transactCred = new Transaction(recip, send, TransCategory.credits, -amount);
    transactCred.setIdentifier(transactDeb.getIdentifier());
    recip.setBalance(recip.getBalance() + amount);
    send.setBalance(send.getBalance() - amount);

    recip.geTransactionsList().addTransaction(transactDeb);
    send.geTransactionsList().addTransaction(transactCred);

  }

  public Transaction[] getTransactionsArray(Integer value) {
    return userlst.getUserById(value).geTransactionsList().toArray();
  }

  public void eraseTransaction(UUID transId, Integer usrId) {
    User user = userlst.getUserById(usrId);
    int hasTr = 0;
    Transaction[] trOfUser = this.getTransactionsArray(user.getId());
    for (Transaction t : trOfUser) {
      if (t.getIdentifier().equals(transId)) {
        hasTr++;
      }
    }
    if (hasTr > 0) {
      user.geTransactionsList().eraseTransactionById(transId);
    } else {
      throw new IllegalTransactionException("Incorrect user Transaction deleting");
    }
  }

  public Transaction[] getInvalidTransactions() {
    TransactionsList transactionsList = new TransactionsLinkedList();
    TransactionsList invTransact = new TransactionsLinkedList();

    for (int i = 0; i < userlst.getUserNumber(); i++) {
      Transaction[] transOfUser = userlst.getUserByIndex(i).geTransactionsList().toArray();
      for (Transaction t : transOfUser) {
        transactionsList.addTransaction(t);
      }
    }

    Transaction[] tr = transactionsList.toArray();

    for (Transaction t : tr) {
      int eq = 0;
      for (Transaction tt : tr) {
        if (t.getIdentifier().equals(tt.getIdentifier())) {
          eq++;
        }
      }
      if (eq < 2) {
        invTransact.addTransaction(t);
      }
    }
    return invTransact.toArray();
  }
}
