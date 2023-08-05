package src.main.java.school21.spring.renders;

import src.main.java.school21.spring.preprocessors.PreProcessor;

public class RenderStandardImpl implements Render{
  private PreProcessor preProcessor;
  public RenderStandardImpl(PreProcessor preProcessor) {
    this.preProcessor = preProcessor;
  }
  @Override
  public void rendering(String message) {
    System.out.println(preProcessor.handler(message));
  }
}
