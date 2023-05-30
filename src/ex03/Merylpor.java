package ex03;

import java.util.Scanner;

public class Merylpor {
    public static void main(String[] args) {
        String result = "";
//        for (int i = 0; i <= 18; ++i) {
//            Scanner in = new Scanner(System.in);
//            String inpNum = in.nextLine();
//            for ()
//        }
    }

    private static int getNumber (String num) {
        int number = 0;
        if (num.equals("1"))
            number = 1;
        if (num.equals("2"))
            number = 2;
        if (num.equals("3"))
            number = 3;
        if (num.equals("4"))
            number = 4;
        if (num.equals("5"))
            number = 5;
        if (num.equals("6"))
            number = 6;
        if (num.equals("7"))
            number = 7;
        if (num.equals("8"))
            number = 8;
        if (num.equals("9"))
            number = 9;
        return number;
    }

    private static int getMinimum (int cur, int next) {
        int min = cur;
        if (next < min)
            min = next;
        return min;
    }
}
