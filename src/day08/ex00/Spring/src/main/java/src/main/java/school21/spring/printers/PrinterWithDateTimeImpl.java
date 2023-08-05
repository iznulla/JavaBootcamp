package src.main.java.school21.spring.printers;

import lombok.Data;
import src.main.java.school21.spring.renders.Render;

@Data
public class PrinterWithDateTimeImpl implements Printer {

  private Render render;

  @Override
  public void print(String print) {

  }
}

