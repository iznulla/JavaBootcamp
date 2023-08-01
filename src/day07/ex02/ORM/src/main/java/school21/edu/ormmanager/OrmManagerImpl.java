package school21.edu.ormmanager;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.stream.Collectors;
import javax.sql.DataSource;
import school21.edu.JdbcManager.HikDataSource;

public class OrmManagerImpl implements OrmManager {

  public OrmManagerImpl() {
    this.init();
  }
  @Override
  public void save(Object entity) {

  }

  @Override
  public void update(Object entity) {

  }

  @Override
  public <T> T fidById(Long id, Class<T> aClass) {
    return null;
  }

  public void init() {
    try {
      DataSource dataSource = HikDataSource.getDs();
      Connection con = dataSource.getConnection();
      Path schemaUrl = Paths.get("/Users/merylpor/Desktop/JavaButcamp/"
          + "src/day07/ex02/ORM/target/classes/schema.sql").normalize();
      String schema = Files.lines(schemaUrl).collect(Collectors.joining("\n"));
      PreparedStatement preparedStatement = con.prepareStatement(schema);
      preparedStatement.execute();
      System.out.println(schema);
    } catch (SQLException | IOException  e) {
      e.printStackTrace();
    }

  }
}
