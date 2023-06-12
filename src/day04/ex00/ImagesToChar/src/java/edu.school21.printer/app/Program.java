package day04.ex00.ImagesToChar.src.java.edu.school21.printer.app;

import day04.ex00.ImagesToChar.src.java.edu.school21.printer.logic.ImagePrinterInCommandLine;

import java.io.IOException;

public class Program {
    public static void main(String[] args) throws IOException {
        ImagePrinterInCommandLine image = new ImagePrinterInCommandLine();
        image.printImg("/Users/merylpor/Desktop/JavaButcamp/src/day04/ex00/it.bmp", '.', '0');
    }
}
