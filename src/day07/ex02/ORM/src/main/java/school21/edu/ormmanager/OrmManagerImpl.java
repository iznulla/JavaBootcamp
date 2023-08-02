package school21.edu.ormmanager;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.stream.Collectors;
import javax.sql.DataSource;
import school21.edu.JdbcManager.HikDataSource;
import school21.edu.annotations.OrmColumnId;
import school21.edu.annotations.OrmEntity;


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
      PreparedStatement statement = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
      Field[] fields = entity.getClass().getDeclaredFields();
      for (int i = 1; i < fields.length; ++i) {
        fields[i].setAccessible(true);
        statement.setObject(i, fields[i].get(entity));
      }
      statement.executeUpdate();
      System.out.println(getQueryDetails(entity) + "\nSuccess Save\n###############################");
    } catch (SQLException | IllegalAccessException e) {
      e.printStackTrace();
    }
  }


  @Override
  public void update(Object entity) {

  }

  @Override
  public <T> T fidById(Long id, Class<T> aClass) {
    try {
      String query = String.format("SELECT * FROM %s WHERE id=?",
          aClass.getAnnotation(OrmEntity.class).table());
      DataSource ds = HikDataSource.getDs();
      Connection con = ds.getConnection();
      PreparedStatement statement = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
      statement.setLong(1, id);
      ResultSet resultSet = statement.executeQuery();
      T obj = aClass.getConstructor().newInstance();
      Field[] fields = obj.getClass().getDeclaredFields();
      resultSet.next();
      for (int i = 1; i < fields.length; ++i) {
        fields[i].setAccessible(true);
        Object value = resultSet.getObject(i + 1);
        fields[i].set(obj, value);
      }
      System.out.println(query);
      con.close();
      return obj;
    } catch (SQLException | InstantiationException
             | IllegalAccessException | NoSuchMethodException |
        InvocationTargetException e) {
      e.printStackTrace();
    }
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
      con.close();
      System.out.println(schema);
    } catch (SQLException | IOException  e) {
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
    for (int i = 1; i < fields.length;++i) {
      query.append(String.format("%s", fields[i].getName()));
      values.append("?");
      if (i != fields.length -1) {
        query.append(",");
        values.append(",");
      }
    }
    query.append(") VALUES (").append(values).append(");");
    return query.toString();
  }
}
