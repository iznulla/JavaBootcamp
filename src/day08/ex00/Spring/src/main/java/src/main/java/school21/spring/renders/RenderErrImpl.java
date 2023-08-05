package src.main.java.school21.spring.renders;

import src.main.java.school21.spring.preprocessors.PreProcessor;

public class RenderErrImpl implements Render{

  private final PreProcessor preProcessor;

  public RenderErrImpl(PreProcessor preProcessor) {
    this.preProcessor = preProcessor;
  }


  @Override
  public void rendering(String message) {
    System.err.println(preProcessor.handler(message));
  }
}
