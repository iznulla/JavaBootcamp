package src.main.java.school21.spring.preprocessors;


public class PreProcessorToUpperImpl implements PreProcessor{

  @Override
  public String handler(String message) { return message.toUpperCase(); }
}
