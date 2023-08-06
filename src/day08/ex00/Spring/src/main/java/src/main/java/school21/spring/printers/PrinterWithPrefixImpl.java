package src.main.java.school21.spring.printers;

import com.sun.istack.internal.NotNull;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import src.main.java.school21.spring.renders.Renderer;

@Data
public class PrinterWithPrefixImpl implements Printer {

  @NotNull
  private final Renderer render;

  private String prefix;

  public void setPrefix(String prefix) {
    this.prefix = prefix;
  }

  @Override
  public void print(String print) {
    render.rendering(String.format("%s %s",this.prefix, print));
  }
}
