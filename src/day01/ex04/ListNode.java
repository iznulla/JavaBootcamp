package day01.ex04;

public class ListNode {

    private Transaction data;
    private ListNode next;
    private ListNode prev;

    public ListNode() {
      this.data = new Transaction();
      this.next = null;
      this.prev = null;
    }

    public ListNode(Transaction value) {
      this.data = value;

    }

    public ListNode(ListNode prev) {
      this.next = null;
      this.prev = prev;
    }

    public ListNode getPrev() {
      return prev;
    }

    public void setPrev(ListNode prev) {
      this.prev = prev;
    }

    public ListNode getNext() {
      return next;
    }

    public void setNext(ListNode next) {
      this.next = next;
    }

    public Transaction getData() {
      return data;
    }
}
