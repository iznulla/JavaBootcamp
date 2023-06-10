package day03.ex02;


import java.util.ArrayList;
import java.util.List;

public class TaskThread extends Thread{

  private final int[] array;
  private final int numThreads;

  public TaskThread(int[] arr, int count) {
    this.array = arr;
    this.numThreads = count;
  }


  public synchronized void run() {
    List<Thread> threads = new ArrayList<>();
    int chunkSize = array.length / numThreads;
    int beginIndex = 0, endIndex = chunkSize;

    for (int i = 1; i <= numThreads; i++) {
      if (i == numThreads)
        endIndex = array.length;
      int[] threadArray = new int[endIndex - beginIndex];
      System.arraycopy(array, beginIndex, threadArray, 0, endIndex - beginIndex);
      Thread thread = new Thread(new Task(threadArray, beginIndex, endIndex, i));
      thread.start();
      threads.add(thread);
      beginIndex = endIndex;
      endIndex += chunkSize;
    }
    for (Thread t : threads) {
      try {
        t.join();
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    }
  }
}

class Task extends Thread {

  int[] array;
  int begin, end, tIndex;
  long sum = 0;

  public Task(int[] arr, int bg, int en, int tNumber) {
    array = arr;
    begin = bg;
    end = en;
    this.tIndex = tNumber;

  }

  public synchronized void run() {
    for (int a : array)
      sum += a;
    System.out.printf("Thread %d: from %d to %d sum is %d\n", tIndex, begin, end, sum);
  }
}