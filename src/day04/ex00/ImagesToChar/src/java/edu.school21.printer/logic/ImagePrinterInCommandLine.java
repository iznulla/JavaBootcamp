package day04.ex00.ImagesToChar.src.java.edu.school21.printer.logic;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImagePrinterInCommandLine {
    File file;
    BufferedImage bfi;

    public ImagePrinterInCommandLine() {
    }

    private void fillBuffer (String filename) {
        file = new File(filename);
        try {
            bfi = ImageIO.read(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void printImg (String filename, char white, char black) {
        fillBuffer(filename);
        for (int i = 0; i < bfi.getHeight(); ++i) {
            for (int j = 0; j < bfi.getWidth(); ++j) {
                int color = bfi.getRGB(j, i);
                System.out.printf("%c", (color == 0xFF000000) ? black : white);
            }
            System.out.println();
        }
    }
}