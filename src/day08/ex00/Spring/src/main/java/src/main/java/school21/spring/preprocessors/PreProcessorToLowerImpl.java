package src.main.java.school21.spring.preprocessors;

import lombok.Data;

@Data
public class PreProcessorToLowerImpl implements PreProcessor{
  public String message;

  @Override
  public String handler(String message) {
    return message.toLowerCase();
  }
}
