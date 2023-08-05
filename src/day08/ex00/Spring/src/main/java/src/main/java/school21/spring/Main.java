package src.main.java.school21.spring;

import src.main.java.school21.spring.preprocessors.PreProcessor;
import src.main.java.school21.spring.preprocessors.PreProcessorToUpperImpl;
import src.main.java.school21.spring.printers.Printer;
import src.main.java.school21.spring.printers.PrinterWithPrefixImpl;
import src.main.java.school21.spring.renders.Render;
import src.main.java.school21.spring.renders.RenderErrImpl;
import src.main.java.school21.spring.renders.RenderStandardImpl;

public class Main {

  public static void main(String[] args) {
    PreProcessor preProcessor = new PreProcessorToUpperImpl();
    Render render = new RenderErrImpl(preProcessor);
    Printer printer = new PrinterWithPrefixImpl(render);
    printer.s
    printer.print("hello");
  }
}