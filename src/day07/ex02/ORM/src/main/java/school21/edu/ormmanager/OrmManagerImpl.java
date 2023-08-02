package school21.edu.ormmanager;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.sql.DataSource;
import jdk.jfr.internal.Options;
import school21.edu.JdbcManager.HikDataSource;
import school21.edu.annotations.OrmColumnId;
import school21.edu.annotations.OrmEntity;
import school21.edu.models.User;

public class OrmManagerImpl implements OrmManager {

  public OrmManagerImpl() {
    this.init();
  }
  @Override
  public void save(Object entity) {
    try {
      String query = getQueryDetails(entity);

      DataSource dataSource = HikDataSource.getDs();
      Connection con = dataSource.getConnection();
      PreparedStatement statement = con.prepareStatement(query);
      Field[] fields = entity.getClass().getDeclaredFields();
      int i = 0;
      for (Field field : fields) {
        statement.setObject(i, field);
      }
      statement.executeUpdate();
      System.out.println(getQueryDetails(entity));
    } catch (SQLException e) {
      e.printStackTrace();
    }

  }
  private String getQueryDetails(Object obj) {
    StringBuilder query = new StringBuilder();
    StringBuilder values = new StringBuilder();
    Class<?> clazz = obj.getClass();
    Field[] fields = clazz.getDeclaredFields();
    OrmEntity ormEntity = clazz.getAnnotation(OrmEntity.class);
    query.append(String.format("INSERT INTO %s "
        + "(", ormEntity.table()));
    if (clazz.getAnnotation(OrmColumnId.class) != null) {
      OrmColumnId ormColumnId = clazz.getAnnotation(OrmColumnId.class);
      query.append(ormColumnId.id());
    }
    for (Field field : fields) {
      if (field.getName().equals("id")) {
        query.append(String.format("%s", field.getName()));
      }
      else {
        query.append(String.format(",%s", field.getName()));
        values.append(",?");
      }
    }
    query.append(") VALUES (?").append(values).append(");");
  return query.toString();
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
