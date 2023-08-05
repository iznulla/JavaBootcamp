package src.main.java.school21.spring.printers;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import src.main.java.school21.spring.renders.Render;

@Data
@RequiredArgsConstructor
public class PrinterWithPrefixImpl implements Printer {

  @NonNull
  private final String prefix;
  private final Render render;

  @Override
  public void print(String print) {
    render.rendering(String.format("%s %s",prefix, print));
  }
}
