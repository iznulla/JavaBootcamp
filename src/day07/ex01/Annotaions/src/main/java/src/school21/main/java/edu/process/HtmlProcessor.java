package src.school21.main.java.edu.process;


import com.google.auto.service.AutoService;
import java.io.BufferedWriter;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import src.school21.main.java.edu.annotations.HtmlForm;
import src.school21.main.java.edu.annotations.HtmlInput;

@SupportedAnnotationTypes(
    value = {"src.school21.main.java.edu.annotations.HtmlForm",
        "src.school21.main.java.edu.annotations.HtmlInput"})
@SupportedSourceVersion(SourceVersion.RELEASE_8)
@AutoService(Processor.class)
public class HtmlProcessor extends AbstractProcessor {

  @Override
  public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
    for (TypeElement annotation : annotations) {
      Set<? extends Element> annotatedElements
          = roundEnv.getElementsAnnotatedWith(annotation);
      for (Element element : annotatedElements) {
        HtmlForm htmlForm = element.getAnnotation(HtmlForm.class);
        String filename = htmlForm.fileName();
        String form = String.format("<form action = \" /%s\" method = \"%s\">", htmlForm.action(),
            htmlForm.method());
        try {
          Path outp = Paths.get("target/classes", filename);
          BufferedWriter bfw = Files.newBufferedWriter(outp);
          bfw.write(form);
          bfw.close();
        } catch (IOException e) {
          e.fillInStackTrace();
        }
      }
    }

    return true;
  }
}
