package day03.ex01;

public class Program {

  public static void main(String[] args) throws InterruptedException {

    if (!args[0].startsWith("--count=") || args.length != 1) {
      System.err.println("Argument failed");
      System.exit(-1);
    }
    int count = Integer.parseInt((args[0].substring("--count=".length())));
    Thread henThread = new Thread(new MyThread(count, "Hen"));
    Thread eggThread = new Thread(new MyThread(count, "egg"));;

    eggThread.start();
    henThread.start();

  }
}

class MyThread extends Thread {

  final Object status = true;
  int count;
  final String result;

  MyThread(int count, String value) {
    this.count = count;
    this.result = value;
  }


  public void run() {
    for (int i = 0; i < count; ++i) {
      synchronized (status) {
        status.notifyAll();
        System.out.println(result);
        try {
          if (i != count -1)
            status.wait();
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
      }
    }
  }
}



