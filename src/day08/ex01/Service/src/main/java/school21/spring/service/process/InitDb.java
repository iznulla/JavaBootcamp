package school21.spring.service.process;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.stream.Collectors;
import javax.sql.DataSource;
import school21.spring.service.JdbcManager.JdbcUtils;

public class InitDb {

  public InitDb() {
  }

  public static void init() {
    try {
      Path schemaUrl = Paths.get("target/classes/schema.sql").normalize();
      if (!Files.exists(schemaUrl)) {
        throw new RuntimeException("File schema.sql not found. Compile the project using "
            + "'mvn clean compile' to generate the file.");
      }
      String schema = Files.lines(schemaUrl).collect(Collectors.joining("\n"));
      PreparedStatement statement = JdbcUtils.preStatement(schema);
      statement.execute();
      statement.close();
      System.out.println(schema);
    } catch (SQLException | IOException e) {
      e.printStackTrace();
    }
  }
}
