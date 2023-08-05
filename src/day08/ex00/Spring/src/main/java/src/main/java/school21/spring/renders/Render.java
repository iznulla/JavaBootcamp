package src.main.java.school21.spring.renders;

import lombok.Data;
import src.main.java.school21.spring.preprocessors.PreProcessor;

public interface Render {
  void rendering(String message);
}
