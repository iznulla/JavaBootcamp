package ex00;

public class Merylpor {
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
