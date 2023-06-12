package day04.ex00.ImagesToChar.src.java.edu.school21.printer.app;

import day04.ex00.ImagesToChar.src.java.edu.school21.printer.logic.ImagePrinterInCommandLine;


public class App {
    public static void main(String[] args) {
        if (checkArgs(args)) {
            ImagePrinterInCommandLine image = new ImagePrinterInCommandLine();
            image.printImg(args[0], args[1], args[2]);
        }

    }
    public static boolean checkArgs(String[] value) {
        boolean check = true;
        if (value[1].length() > 1 || value[2].length() > 1) {
            System.err.println("Invalid Argument " + value[1] + " " + value[2]);
            System.exit(-1);
            check = false;
        }
        if (value.length != 3) {
            System.err.println("Invalid Argument " + value[0]);
            System.exit(-1);
            check = false;
        }
        return check;
    }
}
