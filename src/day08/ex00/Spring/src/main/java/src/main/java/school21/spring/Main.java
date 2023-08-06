package src.main.java.school21.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import src.main.java.school21.spring.preprocessors.PreProcessor;
import src.main.java.school21.spring.preprocessors.PreProcessorToUpperImpl;
import src.main.java.school21.spring.printers.Printer;
import src.main.java.school21.spring.printers.PrinterWithPrefixImpl;
import src.main.java.school21.spring.renders.RenderErrImpl;
import src.main.java.school21.spring.renders.Renderer;


public class Main {

  public static void main(String[] args) {
    PreProcessor preProcessor = new PreProcessorToUpperImpl();
    Renderer renderer = new RenderErrImpl(preProcessor);
    PrinterWithPrefixImpl printer = new PrinterWithPrefixImpl(renderer);
    printer.setPrefix ("Prefix ");
    printer.print ("Hello!");
    ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
    Printer printerErr = context.getBean("printerWithPrefixErrUp", Printer.class);
    printerErr.print("HelloWithPrefixErrBean");
    Printer printerStd = context.getBean("printerWithPrefixStdUp", Printer.class);
    printerStd.print("HelloWithPrefixStdBean");

    Printer printerWithDadeErrLow = context.getBean("printerWithDateErrLow", Printer.class);
    printerWithDadeErrLow.print("HelloDateErrLow");

    Printer printerWithDade = context.getBean("printerWithDateStdUp", Printer.class);
    printerWithDade.print("HelloWithDateStdUp");


  }
}