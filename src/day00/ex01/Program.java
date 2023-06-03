package day00.ex01;

import java.util.Scanner;


public class Program {

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    String str = "true";
    int inputNumber = in.nextInt();
    if (inputNumber <= 1) {
      System.err.println("Illegal Argument");
      in.close();
      System.exit(-1);
    }
    int iterations = 1;
    for (int i = 2; i * i <= inputNumber; ++i) {
        if (inputNumber % i == 0) {
            str = "false";
            break;
        } else {
            ++iterations;
        }
    }
    System.out.println(str + " " + iterations);
    in.close();
  }
}
