package day03.ex03;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Program {

  public static void main(String[] args) {

    if (args.length < 1 || !args[0].startsWith("--threadsCount=")) {
      System.out.println("Please make sure you write like in the example --threadsCount=3\n");
      System.exit(-1);
    }
    try {
      int numThreads = Integer.parseInt(args[0].substring("--threadsCount=".length()));
      UrlParser urlParser = new UrlParser("./file_urls.txt");
      ExecutorService executorService = Executors.newFixedThreadPool(numThreads);
      Vector<String> url = urlParser.getUrls();
      for (int i = 0; i < url.size(); i++) {
        DownloadThread dwn = new DownloadThread(url.elementAt(i), i);
        executorService.execute(dwn);
      }
      executorService.shutdown();
    } catch (IllegalArgumentException e) {
      System.err.println("Pleas put legal argument and try again\n");
      System.exit(-1);
    }


  }
}

class DownloadThread implements Runnable {

  String urls;
  Integer fileNumber;

  public DownloadThread(String url, Integer number) {
    urls = url;
    fileNumber = number;
  }

  public void dwn(String ur) {
    try {
      URL url = new URL(ur);
      BufferedInputStream bis = new BufferedInputStream(url.openStream());
      String[] getFilename = ur.split("/");
      String filename = getFilename[getFilename.length - 1];
      FileOutputStream fis = new FileOutputStream(filename);
      byte[] buffer = new byte[1024];
      int count = 0;
      while ((count = bis.read(buffer, 0, 1024)) != -1) {
        fis.write(buffer, 0, count);
      }
      fis.close();
      bis.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public synchronized void run() {

      String[] threadId = Thread.currentThread().getName().split("-");
      System.out.println("Thread-" + threadId[threadId.length -1]
          + " start download file number " + (fileNumber + 1));
      dwn(urls);
      System.out.println("Thread-" + threadId[threadId.length -1]
          + " finish download file number " + (fileNumber + 1));
    }
}

class UrlParser {

  private final Vector<String> urls;

  UrlParser(String filename) {
    urls = new Vector<>();
    readFile(filename);
  }

  public Vector<String> getUrls() {
    return urls;
  }

  private void readFile(String filename) {
    try {
      String url;
      FileReader fileReader = new FileReader(filename);
      BufferedReader buf = new BufferedReader(fileReader);
      while ((url = buf.readLine()) != null) {
        urls.add(url);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}