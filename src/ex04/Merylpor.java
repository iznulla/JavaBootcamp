package ex04;

import java.util.Scanner;

public class Merylpor {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String inpLine = in.nextLine();
        char[] line = inpLine.toCharArray();
        in.close();

        int countLine = 0;
        char[][] array = new char[65535][2];

        countLine = parser(line, array);
        sort(array, countLine);
        showResult(array);
    }

    private static int parser(char[] chLine, char[][] chArray) {
        int count = 0;
        if (chLine.length >= 999) {
            System.err.println("IndexOutOfRange");
            System.exit(-1);
        }
        for (char c : chLine) {
            for (int j = 0; j < chArray.length; ++j) {
                if (chArray[j][1] == 0) {
                    chArray[j][0] = c;
                    chArray[j][1] += 1;
                    count++;
                    break;
                } else if (chArray[j][0] == c) {
                    chArray[j][1] += 1;
                    break;
                }
            }
        }
        return count;
    }

    private static void sort(char[][] chArray, int count) {
        for (int j = 0; j < count; ++j) {
            for (int i = 0; i < count - j - 1; ++i) {
                if (chArray[i + 1][1] > chArray[i][1]) {
                    char[] temp = chArray[i];
                    chArray[i] = chArray[i + 1];
                    chArray[i + 1] = temp;
                }
                if (chArray[i + 1][0] < chArray[i][0] && chArray[i + 1][1] == chArray[i][1]) {
                    char[] temp = chArray[i];
                    chArray[i] = chArray[i + 1];
                    chArray[i + 1] = temp;
                }
            }
        }
    }

    private static void showResult(char[][] chArray) {
        double maximumSizeChar = chArray[0][1];
        double sizeShowLine = (double) chArray[0][1] / 10.;

        for (double j = maximumSizeChar + sizeShowLine + sizeShowLine; j >= 0; j -= sizeShowLine) {
            for (int i = 0; (i < 10) && (chArray[i][0] != 0); ++i) {
                if (chArray[i][1] >= j - sizeShowLine - sizeShowLine) {
                    if (chArray[i][1] >= j - sizeShowLine) {
                        if (j - sizeShowLine < 0) {
                            System.out.printf("%3c ", chArray[i][0]);
                        } else {
                            System.out.printf("%3c ", '#');
                        }
                    } else {
                        System.out.printf("%3d ", (int) chArray[i][1]);
                    }
                }
            }
            System.out.println();
        }
    }
}
