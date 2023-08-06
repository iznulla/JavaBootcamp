package src.main.java.school21.spring.preprocessors;



public class PreProcessorToLowerImpl implements PreProcessor{
  @Override
  public String handler(String message) {
    return message.toLowerCase();
  }
}
