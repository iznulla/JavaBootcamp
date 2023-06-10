package day03.ex02;


import java.util.Arrays;

public class Program {

  public static void main(String[] args) throws InterruptedException {



    if (!args[0].startsWith("--arraySize=") ||
        !args[1].startsWith("--threadsCount=") || !check(args[0], args[1])) {
      System.out.println("Please make sure you write like in the example --arraySize=13 --threadsCount=3\n");
      System.exit(-1);
    }
    int[] array = new int[Integer.parseInt(args[0].substring("--arraySize=".length()))];
    int numThreads = Integer.parseInt(args[1].substring("--threadsCount=".length()));
    Arrays.fill(array, 1);
    if (array.length > 2000000 || numThreads > array.length) {
      System.out.println("Index out of range, try again with correct number\n");
    }
    Thread taskThread = new Thread(new TaskThread(array, numThreads));
    taskThread.start();

    taskThread.join();

    System.out.println(array.length);

  }
  public static boolean check(String checkOne, String checkTwo) {
    return checkOne.split("=").length == 2 && checkTwo.split("=").length == 2;
  }
}

