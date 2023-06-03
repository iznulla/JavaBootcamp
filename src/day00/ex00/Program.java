package day00.ex00;

public class Program {

  public static void main(String[] args) {
    int temp = 479598;
    int result = 0;
    result += temp % 10;
    temp /= 10;
    result += temp % 10;
    temp /= 10;
    result += temp % 10;
    temp /= 10;
    result += temp % 10;
    temp /= 10;
    result += temp % 10;
    temp /= 10;
    result += temp % 10;
    System.out.println(result);
  }
}
