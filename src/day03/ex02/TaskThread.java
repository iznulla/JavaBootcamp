package day03.ex02;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TaskThread implements Runnable {

  private final int[] array;
  private final int numThreads;

  public TaskThread(int[] arr, int count) {
    this.array = arr;
    this.numThreads = count;
  }

  public synchronized void run() {
    int chunkSize = array.length / numThreads;
    int beginIndex = 0, endIndex = chunkSize;
    ExecutorService executor = Executors.newFixedThreadPool(numThreads);
    for (int i = 1; i <= numThreads; i++) {
      if (i == numThreads)
        endIndex = array.length;
//      int[] threadArray = new int[endIndex - beginIndex];
//      System.arraycopy(array, beginIndex, threadArray, 0, endIndex - beginIndex);
      Task thread = new Task(array, beginIndex, endIndex, i);
      executor.execute(thread);
      beginIndex = endIndex;
      endIndex += chunkSize;
    }
    executor.shutdown();
  }
}

class Task implements Runnable {

  int[] array;
  int begin, end, tIndex;
  public int sum = 0;
  public int totalSum = 0;

  public Task(int[] arr, int bg, int en, int tNumber) {
    array = arr;
    begin = bg;
    end = en;
    this.tIndex = tNumber;

  }

  public synchronized void run() {
    sum = 0;
    for (int i = begin; i < end; i++) {
      sum += array[i];
    }
    System.out.printf("Thread %d: from %d to %d sum is %d\n", tIndex, begin, end, sum);
  }
}