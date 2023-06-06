package day02.ex00;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Program {

  public static void main(String[] args) {
    FindSignature signatures = new FindSignature();
    int size = signatures.getSignaturesFromSignature(
        "/Users/merylpor/Desktop/JavaButcamp/src/day02/ex00/signatures.txt");
    FileWriter outputStream = null;
    try {
      Scanner in = new Scanner(System.in);
      outputStream = new FileWriter("/Users/merylpor/Desktop/JavaButcamp/src/day02/ex00/resulT.txt",
          true);
      while (in.hasNext()) {
        String path = in.next();
        if (path.equals("42")) {
          break;
        }
        String sign = signatures.getSignature(path, size);
        String result = signatures.getCheckSignature(signatures.signFile, sign);
        outputStream.write(result + "\n");
        System.out.println("PROCESSED");
      }
      in.close();
    } catch (IOException e) {
      throw new RuntimeException(e);
    } finally {
      try {
        assert outputStream != null;
        outputStream.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}
