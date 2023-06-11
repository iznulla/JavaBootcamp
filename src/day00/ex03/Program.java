package day00.ex03;

import java.util.Scanner;

public class Program {

  public static void main(String[] args) {
    int res = 0;
    for (int i = 0; i < 18; ++i) {
      int rr = 10;
      Scanner in = new Scanner(System.in);
      String inpNum = in.next();
      rr = getMinimum(rr, getNumber(inpNum));
      if (inpNum.equals("42")) {
        break;
      }
      in.useDelimiter("");
      while (in.hasNext()) {
        inpNum = in.next();
          if (inpNum.equals(" ")) {
              continue;
          }
          if (inpNum.equals("\n")) {
              break;
          }
        rr = getMinimum(rr, getNumber(inpNum));
      }

      res = completeInt(res, rr);
    }
    res = reverseNum(res);
    while (res > 0) {
      String outt = "";
      int count = res % 10;
        for (int i = 0; i < count; ++i) {
            outt += "=";
        }
      System.out.println(outt + ">");
      res /= 10;
    }
  }

  private static int getNumber(String num) {
    int number = 0;
      if (num.equals("1")) {
          number = 1;
      } else if (num.equals("2")) {
          number = 2;
      } else if (num.equals("3")) {
          number = 3;
      } else if (num.equals("4")) {
          number = 4;
      } else if (num.equals("5")) {
          number = 5;
      } else if (num.equals("6")) {
          number = 6;
      } else if (num.equals("7")) {
          number = 7;
      } else if (num.equals("8")) {
          number = 8;
      } else if (num.equals("9")) {
          number = 9;
      } else if (num.equals("42")) {
          number = 42;
      } else {
          System.err.println("IllegalArgument");
          System.exit(-1);
      }
    return number;
  }

  private static int getMinimum(int cur, int next) {
    int min = cur;
      if (next < min) {
          min = next;
      }
    return min;
  }

  private static int completeInt(int cur, int inp) {
    int result = cur;
    result = result * 10 + inp;
    return result;
  }

  private static int reverseNum(int num) {
    int result = 0;
    while (num > 0) {
      int count = num % 10;
      result = completeInt(result, count);
      num /= 10;
    }
    return result;
  }
}