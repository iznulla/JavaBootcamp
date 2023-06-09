package day03.ex01;

public class Program {

  public static void main(String[] args) throws InterruptedException {

    if (!args[0].startsWith("--count=") || args.length != 1) {
      System.err.println("Argument failed");
      System.exit(-1);
    }
    int count = Integer.parseInt((args[0].substring("--count=".length())));
    MyThread henThread = new MyThread(count, "Hen", false);
    MyThread eggThread = new MyThread(count, "Egg", false);

    eggThread.start();
    henThread.start();

  }
}

class MyThread extends Thread {

  private boolean status = false;
  int count;
  String result;

  MyThread(int count, String value, boolean status) {
    this.count = count;
    this.result = value;
    this.status = status;
  }

  public boolean getStatus() {
    return status;
  }

  public void setStatus(boolean value) {
    this.status = value;
  }

  public void run() {
    for (int i = 0; i < count; ++i) {
      System.out.println(this.result);
      if (status)
        this.get();

    }
  }

  public void get() {
    while (!this.getStatus()) {
      try {
        this.setStatus(true);
        wait();
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
      this.setStatus(false);
      notifyAll();
    }
  }

  public void put() {
    while (!this.getStatus()) {
      try {
        this.setStatus(false);
        wait();
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
      this.setStatus(true);
      notifyAll();
    }
  }
}



