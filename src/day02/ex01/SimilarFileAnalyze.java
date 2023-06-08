package day02.ex01;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class SimilarFileAnalyze {

  private final HashMap<String, String> allSymbols;
  private Vector<String> vFileOne;
  private Vector<String> vFileTwo;
  private Vector<Integer> vector_1;
  private Vector<Integer> vector_2;

  private double similar;

  public SimilarFileAnalyze() {
    allSymbols = new HashMap<>();
    vFileOne = new Vector<>();
    vFileTwo = new Vector<>();
    vector_1 = new Vector<>();
    vector_2 = new Vector<>();
  }

  public void fillMap(String path) {
    try {
      FileReader sin = new FileReader(path);;
      BufferedReader bfread = new BufferedReader(sin);
      String data;
      while ((data = bfread.readLine()) != null) {
        for (String s : data.split(" ")) {
          allSymbols.put(s, null);
        }
      }
      sin.close();
      bfread.close();
    } catch (IOException e) {
      throw new RuntimeException("File Not Found");
    }
  }

  public void readForFillMap(String fileOne, String fileTwo) {
    fillMap(fileOne);
    fillMap(fileTwo);
    vFileOne = fillData(fileOne);
    vFileTwo = fillData(fileTwo);
    vector_1 = fillVector(vFileOne);
    vector_2 = fillVector(vFileTwo);
  }

  public Vector<String> fillData(String path) {
    Vector<String> vc = new Vector<>();
    try {
      FileReader sin = new FileReader(path);
      BufferedReader bfread = new BufferedReader(sin);
      String data;
      while ((data = bfread.readLine()) != null) {
        vc.addAll(Arrays.asList(data.split(" ")));
      }
      sin.close();
      bfread.close();
    } catch (IOException e) {
      throw new RuntimeException("file not found");
    }
    return vc;
  }

  public Vector<Integer> fillVector(Vector<String> value) {
    Vector<Integer> vc = new Vector<>();
    int i = 0;
    for (Map.Entry m : allSymbols.entrySet()) {
      int count = 0;
      for (String s : value) {
        if (m.getKey().equals(s))
          count++;
      }
      vc.add(count);
    }
    return vc;
  }

  public void calcSimilar(String fileOne, String fileTwo) {
    readForFillMap(fileOne, fileTwo);
    double numerator = 0, dominator, similator, A = 0, B = 0;
    for (int i = 0; i < vector_1.size(); ++i) {
      numerator += (vector_1.get(i) * vector_2.get(i));
    }
    for (int i = 0; i < vector_1.size(); ++i) {
      A += (vector_1.get(i) * vector_1.get(i));
      B += (vector_2.get(i) * vector_2.get(i));
    }
    A = Math.sqrt(A);
    B = Math.sqrt(B);
    dominator = A * B;
    similator = numerator / dominator;
    similator = ((int) (similator * 100)) / 100.0;
    writeDictionary();
    System.out.printf("%.2f\n", similator);
  }

  private void writeDictionary () {
    FileWriter fiw = null;
    try {
      fiw = new FileWriter("Dictionary.txt");
      for (Map.Entry m : allSymbols.entrySet()) {
        fiw.write(m.getKey().toString() + "\n");
      }
      fiw.close();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

}
