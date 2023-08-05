package src.main.java.school21.spring.printers;

import com.sun.istack.internal.NotNull;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import src.main.java.school21.spring.renders.Render;

@Data
@RequiredArgsConstructor
public class PrinterWithPrefixImpl implements Printer {

  @NotNull
  private final Render render;

  private String prefix;

  @Override
  public void print(String print) {
    render.rendering(String.format("%s %s",this.prefix, print));
  }
}
