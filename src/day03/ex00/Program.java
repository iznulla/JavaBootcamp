package day03.ex00;

public class Program {

  public static void main(String[] args) throws InterruptedException {

    if (!args[0].startsWith("--count=") || args.length != 1) {
      System.err.println("Argument failed");
      System.exit(-1);
    }
    int count = Integer.parseInt((args[0].substring("--count=".length())));
    MyThread henThread = new MyThread(count, "Hen");
    MyThread eggThread = new MyThread(count, "Egg");

    eggThread.start();
    henThread.start();

    henThread.join();
    eggThread.join();
    for (int i = 0; i < count; ++i) {
      System.out.println("Human");
    }
  }
}

class MyThread extends Thread {

  int count;
  String result;
  MyThread(int count, String value) {
    this.count = count;
    this.result = value;
  }

  public void run() {
    for (int i = 0; i < count; ++i) {
      System.out.println(this.result);
    }
  }
}