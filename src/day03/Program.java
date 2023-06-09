package day03;

public class Program {

  public static void main(String[] args) throws InterruptedException {

    if (!args[0].startsWith("--count=") || args.length != 1) {
      System.err.println("Argument failed");
      System.exit(-1);
    }
    int count = Integer.parseInt((args[0].substring("--count=".length())));
    HenThread henThread = new HenThread(count);
    EggThread eggThread = new EggThread(count);

    eggThread.start();
    henThread.start();

    henThread.join();
    eggThread.join();
    for (int i = 0; i < count; ++i) {
      System.out.println("Human");
    }
  }
}

class HenThread extends Thread {
  int count;
  HenThread(int count) {
    this.count = count;
  }

  public void run() {
    for (int i = 0; i < count; ++i)
      System.out.println("Hen");
  }
}

class EggThread extends Thread {
  int count;
  EggThread(int count) {
    this.count = count;
  }

  public void run() {
    for (int i = 0; i < count; ++i)
      System.out.println("Egg");
  }
}