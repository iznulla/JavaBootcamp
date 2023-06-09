package day02.ex00;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Program {

  public static void main(String[] args) {
    String report = "./result.txt";
    String pathToSignatures = "signatures.txt";
//    System.out.println(Files.exists(Paths.get(pathToSignatures)));

    FindSignature signatures = new FindSignature();
    int size = signatures.getSignaturesFromSignature(
        pathToSignatures);
    FileWriter outputStream = null;
    try {
      Scanner in = new Scanner(System.in);
      outputStream = new FileWriter(report,
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
