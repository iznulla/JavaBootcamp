package day01.ex03;

import java.util.UUID;

public class TransactionsLinkedList implements TransactionsList {

  public TransactionsLinkedList() {
    this.head = null;
    this.tail = null;
  }

//  public TransactionsLinkedList(Transaction value) {
//    this.addTransaction(value);
//  }

  private ListNode head;
  private ListNode tail;
  int size = 0;

  @Override
  public void addTransaction(Transaction value) {
    ListNode node = new ListNode(value);
    if (this.tail != null) {
      node.setPrev(tail);
      tail.setNext(node);
      tail = node;
    } else {
      this.tail = head = node;
    }
    size++;
  }

  @Override
  public void eraseTransactionById(UUID value) {
    ListNode node = this.head;
    for (int i = 0; i < this.size; ++i) {
      if (node.getData().getIdentifier().equals(value)) {
        node.getPrev().setNext(node.getNext());
        this.size--;
        return;
      }
      node = node.getNext();
    }
    throw new TransactionNotFoundException("Transaction not found");
  }

  @Override
  public Transaction[] toArray() {
    Transaction[] array = new Transaction[this.size];
    ListNode node = this.head;
    for (int i = 0; i < this.size; ++i) {
      array[i] = node.getData();
      node = node.getNext();
    }
    return array;
  }
}
