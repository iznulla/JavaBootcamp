package day00.ex02;

import jdk.nashorn.internal.ir.WhileNode;

import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        int count = 0;
        while (true) {
            Scanner in = new Scanner(System.in);
            int number = in.nextInt();
            if (number == 42) {
                System.out.println(count);
                break;
            }
            if (isPrime(summa(number)))
                ++count;
        }

    }

    private static int summa(int number) {
        int result = 0;
        while (number > 0) {
            result += number % 10;
            number /= 10;
        }
        System.out.println(result);
        return result;
    }

    private static boolean isPrime(int number) {
        if (number <= 1) {
            System.err.println("Illegal Argument");
            System.exit(-1);
        }
        for (int i = 2; i * i <= number; ++i) {
            if (number % i == 0)
                return false;
        }
        return true;
    }
}
