package src.main.java.school21.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import src.main.java.school21.spring.preprocessors.PreProcessor;
import src.main.java.school21.spring.preprocessors.PreProcessorToUpperImpl;
import src.main.java.school21.spring.printers.Printer;
import src.main.java.school21.spring.printers.PrinterWithPrefixImpl;
import src.main.java.school21.spring.renders.Render;
import src.main.java.school21.spring.renders.RenderErrImpl;
import src.main.java.school21.spring.renders.RenderStandardImpl;

public class Main {

  public static void main(String[] args) {
    ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
    Printer printer = context.getBean("prinerWithPrefix", Printer.class);
    PreProcessor preProcessor = new PreProcessorToUpperImpl();
    printer.print("Hello");

  }
}