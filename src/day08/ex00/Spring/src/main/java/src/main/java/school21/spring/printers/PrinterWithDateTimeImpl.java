package src.main.java.school21.spring.printers;

import com.sun.istack.internal.NotNull;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import lombok.Data;
import src.main.java.school21.spring.renders.Renderer;

@Data
public class PrinterWithDateTimeImpl implements Printer {

  @NotNull
  private final Renderer render;
  private LocalDateTime localDateTime = LocalDateTime.now();

  @Override
  public void print(String print) {
    render.rendering(String.format("%s %s", localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), print));
  }
}

