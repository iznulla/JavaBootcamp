package day03.ex03;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Program {

  public static void main(String[] args) {

    List<Thread> threads = new ArrayList<>();

    for (int i = 1; i <= 3; i++) {
      DownloadThread dwn = new DownloadThread("./file_urls.txt", i);
      threads.add(dwn);
      dwn.start();
    }
    for (Thread thr : threads) {
      try {
        thr.join();
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    }
  }
}

class DownloadThread extends Thread{
  UrlParser urls;
  String[] urlArray;
  int index;


  public DownloadThread(String urlFilename, int urlIndex) {
    urls = new UrlParser(urlFilename);
    urlArray =  urls.getUrls();
    index = urlIndex;
  }

  public void dwn(int index) {
    try {
      URL url = new URL(urlArray[index]);
      BufferedInputStream bis = new BufferedInputStream(url.openStream());
      String[] getFilename = urlArray[index].split("/");
      String filename = getFilename[getFilename.length -1];
      FileOutputStream fis = new FileOutputStream(filename);
      byte[] buffer = new byte[1024];
      int count = 0;
      while ((count = bis.read(buffer, 0, 1024)) != -1) {
        fis.write(buffer, 0, count);
      }
      fis.close();
      bis.close();
    }
    catch (IOException e) {
      e.printStackTrace();
    }
  }
  public synchronized void run() {
    for (int i = 0; i < this.urlArray.length; i++) {
      dwn(index);
      System.out.println(this.getName() + " start download file number " + index);
    }
  }

}
class UrlParser {
  private final List<String> urls;

  UrlParser(String filename) {
    urls = new ArrayList<>();
    readFile(filename);
  }

  public String[] getUrls() {
    return urls.toArray(new String[0]);
  }
  private void readFile(String filename) {
    try {
      String url;
      FileReader fileReader = new FileReader(filename);
      BufferedReader buf = new BufferedReader(fileReader);
      while ((url = buf.readLine()) != null)
        urls.add(url);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}