package day02.ex00;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

public class FindSignature {

  public LinkedList<String> signFile;

  public FindSignature() {
    signFile = new LinkedList<>();
  }

  public int getSignaturesFromSignature(String path) {
    int maxSize = 0;
    try {
      Scanner file = new Scanner(new File(
          path));
      while (file.hasNext()) {
        String in = file.nextLine();
        String[] first = in.split(", ");
        if (maxSize < first[1].length()) {
          maxSize = first[1].length();
        }
        signFile.add(first[0] + " " + first[1]);
      }
      file.close();
    } catch (
        FileNotFoundException ex) {
      throw new RuntimeException(ex);
    }
    return maxSize;
  }

  public String getSignature(String path, int size) {
    String sign = "";
    try {
      FileInputStream file = new FileInputStream(path);
      BufferedInputStream bin = new BufferedInputStream(file);
      int data = bin.read();
      for (int i = 0; i < size / 2 - 3; ++i) {
        assert false;
        sign += String.format("%02X ", data);
        data = bin.read();
      }
      bin.close();
      file.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return sign;
  }

  public String getCheckSignature(LinkedList<String> signForm, String signCheck) {
    String result = "UNDEFINED";
    for (String s : signForm) {
      String[] splt = s.split(" ");
      if (signCheck.startsWith(splt[1])) {
        result = splt[0];
      }
    }
    return result;
  }
}
