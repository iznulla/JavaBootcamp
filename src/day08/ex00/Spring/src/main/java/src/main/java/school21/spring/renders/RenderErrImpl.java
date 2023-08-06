package src.main.java.school21.spring.renders;

import lombok.Data;
import lombok.NonNull;
import src.main.java.school21.spring.preprocessors.PreProcessor;

@Data
public class RenderErrImpl implements Renderer {

  @NonNull
  private final PreProcessor preProcessor;

  @Override
  public void rendering(String message) {
    System.err.println(preProcessor.handler(message));
  }
}
