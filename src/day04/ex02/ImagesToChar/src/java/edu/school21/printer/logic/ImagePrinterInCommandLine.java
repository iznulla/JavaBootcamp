package edu.school21.printer.logic;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

import com.beust.jcommander.Parameters;
import com.diogonunes.jcdp.color.ColoredPrinter;
import com.diogonunes.jcdp.color.api.Ansi;

@Parameters(separators = "=")
public class ImagePrinterInCommandLine {

    BufferedImage bfi;
    @Parameter(names = "--white")
    private String whiteReplace;
    @Parameter(names = "--black")
    private String blackReplace;



    public ImagePrinterInCommandLine() {
    }

    public void printImg(String[] args) {
        JCommander.newBuilder().addObject(this).build().parse(args);
        ColoredPrinter whiteColor =
                new ColoredPrinter.Builder(1, false).foreground(Ansi.FColor.valueOf(whiteReplace)).build();
        ColoredPrinter blackColor =
                new ColoredPrinter.Builder(1, false).foreground(Ansi.FColor.valueOf(blackReplace)).build();
        try {
            InputStream in = this.getClass()
                    .getClassLoader().getResourceAsStream("resources/it.bmp");
            assert in != null;
//            FileInputStream in = new FileInputStream("target/resources/it.bmp");
            bfi = ImageIO.read(in);
//            in.close();
        } catch (IOException e) {
            throw new RuntimeException("Failed IO file, put correct path");
        }
        for (int i = 0; i < bfi.getHeight(); ++i) {
            for (int j = 0; j < bfi.getWidth(); ++j) {
                int color = bfi.getRGB(j, i);
                if (color != 0xFF000000)
                    whiteColor.print('\u2588');
                else
                    blackColor.print('\u2588');
            }
            System.out.println();
        }
    }
}