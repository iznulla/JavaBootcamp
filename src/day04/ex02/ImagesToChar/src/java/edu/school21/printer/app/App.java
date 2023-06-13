package edu.school21.printer.app;

import edu.school21.printer.logic.ImagePrinterInCommandLine;


public class App {
    public static void main(String[] args) {
        ImagePrinterInCommandLine image = new ImagePrinterInCommandLine();
        image.printImg(args);


    }

    public static boolean checkArgs(String[] value) {
        boolean check = true;
        if (value[0].length() > 1 || value[1].length() > 1) {
            System.err.println("Invalid Argument " + value[0] + " " + value[1]);
            System.exit(-1);
            check = false;
        }
        if (value.length != 2) {
            System.err.println("Invalid Argument " + value[0]);
            System.exit(-1);
            check = false;
        }
        return check;
    }
}
