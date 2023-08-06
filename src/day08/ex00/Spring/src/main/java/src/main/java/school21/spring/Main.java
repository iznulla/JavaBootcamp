package src.main.java.school21.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import src.main.java.school21.spring.printers.Printer;


public class Main {

  public static void main(String[] args) {
    ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
    Printer printerErr = context.getBean("printerWithPrefixErrUp", Printer.class);
    printerErr.print("Hello");
    Printer printerStd = context.getBean("printerWithPrefixStdUp", Printer.class);
    printerStd.print("Hello");

    Printer printerWithDadeErrLow = context.getBean("printerWithDateErrLow", Printer.class);
    printerWithDadeErrLow.print("HelloDateErrLow");

    Printer printerWithDade = context.getBean("printerWithDateStdUp", Printer.class);
    printerWithDade.print("HelloWithDateStdUp");

  }
}