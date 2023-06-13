package edu.school21.printer.logic;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

public class ImagePrinterInCommandLine {
    FileInputStream file;
    BufferedImage bfi;

    public ImagePrinterInCommandLine() {
    }
    public void printImg (String filename, String white, String black) {

        try {
            file = new FileInputStream(filename);
            bfi = ImageIO.read(file);
            file.close();
        } catch (IOException e) {
            throw new RuntimeException("Failed IO file, put correct path");
        }
        for (int i = 0; i < bfi.getHeight(); ++i) {
            for (int j = 0; j < bfi.getWidth(); ++j) {
                int color = bfi.getRGB(j, i);
                System.out.printf("%s", (color == 0xFF000000) ? black : white);
            }
            System.out.println();
        }
    }
}