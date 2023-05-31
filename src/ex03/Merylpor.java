package ex03;

import java.util.Scanner;

public class Merylpor {
    public static void main(String[] args) {
        int res = 0;
        for (int i = 0; i <= 18; ++i) {
            int rr = 10;
            Scanner in = new Scanner(System.in);
            in.useDelimiter("");
            while (in.hasNext()) {
                char inpNum = in.next().charAt(0);
                if (inpNum == ' ')
                    continue;
                if (inpNum == '\n' || inpNum == 42)
                    break;
                rr = getMinimum(rr, getNumber(inpNum));
            }
            res = completeInt(res, rr);
        }
        res = reverseNum(res);
        while (res > 0) {
            String outt = "";
            int count = res % 10;
            for (int i = 0; i < count; ++i)
                outt += "=";
            System.out.println(outt + ">");
            res /= 10;
        }
    }

    private static int getNumber (char num) {
        int number = 0;
        if (num == '1')
            number = 1;
        if (num == '2')
            number = 2;
        if (num == '3')
            number = 3;
        if (num == '4')
            number = 4;
        if (num == '5')
            number = 5;
        if (num == '6')
            number = 6;
        if (num == '7')
            number = 7;
        if (num == '8')
            number = 8;
        if (num == '9')
            number = 9;
        return number;
    }

    private static int getMinimum (int cur, int next) {
        int min = cur;
        if (next < min)
            min = next;
        return min;
    }

    private static int completeInt(int cur, int inp) {
        int result = cur;
        result = result * 10 + inp;
        return result;
    }

    private static int reverseNum(int num) {
        int rr = 0;
        while (num > 0) {
            int count = num % 10;
            rr = completeInt(rr, count);
            num /= 10;
        }
        return rr;
    }
}
