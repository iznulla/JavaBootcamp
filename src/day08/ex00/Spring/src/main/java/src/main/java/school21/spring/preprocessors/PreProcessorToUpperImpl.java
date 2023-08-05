package src.main.java.school21.spring.preprocessors;

import lombok.Data;

@Data
public class PreProcessorToUpperImpl implements PreProcessor{
  private String message;
  @Override
  public String handler(String message) { return message.toUpperCase(); }
}
