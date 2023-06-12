package day03.ex02;


import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TaskThread implements Callable<Integer> {

  private final int[] array;
  private final int numThreads;

  public TaskThread(int[] arr, int count) {
    this.array = arr;
    this.numThreads = count;
  }

  public Integer call() throws ExecutionException{
    Integer totalSum = 0;
    int chunkSize = array.length / numThreads;
    int beginIndex = 0, endIndex = chunkSize;
    ExecutorService executor = Executors.newFixedThreadPool(numThreads);
    for (int i = 1; i <= numThreads; ++i) {
      if (i == numThreads)
        endIndex = array.length;
      Task thread = new Task(array, beginIndex, endIndex, i);
      totalSum += thread.call();
      beginIndex = endIndex;
      endIndex += chunkSize;
    }
    executor.shutdown();
  return totalSum;
  }
}

class Task implements Callable<Integer>  {

  int[] array;
  int begin, end, tIndex;
  public Integer sum = 0;

  public Task(int[] arr, int bg, int en, int tNumber) {
    array = arr;
    begin = bg;
    end = en;
    this.tIndex = tNumber;

  }

  public Integer call() {
    sum = 0;
    for (int i = begin; i < end; i++) {
      sum += array[i];
    }
    System.out.printf("Thread %d: from %d to %d sum is %d\n", tIndex, begin, end, sum);
  return sum;
  }
}