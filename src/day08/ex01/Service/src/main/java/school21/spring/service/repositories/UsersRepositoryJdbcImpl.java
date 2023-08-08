package school21.spring.service.repositories;

import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TransferQueue;
import java.util.stream.Collectors;
import javax.sql.DataSource;
import lombok.Data;
import lombok.NonNull;
import school21.spring.service.JdbcManager.JdbcUtils;
import school21.spring.service.annotations.OrmColumnId;
import school21.spring.service.annotations.OrmEntity;
import school21.spring.service.models.User;

@Data
public class UsersRepositoryJdbcImpl implements UsersRepository {


  @NonNull
  private final DataSource dataSource;


  @Override
  public User findById(Long id) {
    User user = new User("n");
    OrmEntity ormEntity = user.getClass().getAnnotation(OrmEntity.class);
    String query = String.format("SELECT * FROM %s WHERE id=?;", ormEntity.table());
    try (PreparedStatement ps = dataSource.getConnection().prepareStatement(query)) {
      ps.setLong(1, id);
      ResultSet resultSet = ps.executeQuery();

      Field[] fields = user.getClass().getDeclaredFields();
      System.out.println(fields.length);
      long uId;
      resultSet.next();
      uId = resultSet.getLong("id");
      for (int i = 1; i < fields.length; i++) {
        fields[i].setAccessible(true);
        fields[i].set(user, resultSet.getObject(i+1).toString());
      }
      user.setIdentifier(uId);
      return user;
    } catch (SQLException e) {
      e.fillInStackTrace();
    } catch (IllegalAccessException e) {
      throw new RuntimeException(e);
    }
    return null;
  }

  @Override
  public List<User> findAll() {
    return null;
  }

  @Override
  public void save(User entity) {
    try {
      String query = getQueryDetails(entity, "save");
      PreparedStatement ps = dataSource.getConnection()
          .prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
      Field[] fields = entity.getClass().getDeclaredFields();
      for (int i = 1; i < fields.length; i++) {
        fields[i].setAccessible(true);
        ps.setObject(i, fields[i].get(entity));
      }
      if (ps.executeUpdate() == 0) {
        throw new RuntimeException("Object is not added");
      }
      ResultSet resultSet = ps.getGeneratedKeys();
      resultSet.next();
      entity.setIdentifier(resultSet.getLong("id"));
    } catch (SQLException | IllegalAccessException e) {
      e.fillInStackTrace();
    }
  }

  @Override
  public void update(User entity) {
    String query = getQueryDetails(entity, "update");
    try {
      PreparedStatement ps = dataSource.getConnection().prepareStatement(query);
      Field[] fields = entity.getClass().getDeclaredFields();
      fields[0].setAccessible(true);
      ps.setObject(fields.length, fields[0].get(entity));
      System.out.println(fields[0].get(entity));
      for (int i = 1; i < fields.length; i++) {
        fields[i].setAccessible(true);
        ps.setObject(i, fields[i].get(entity));
      }
      if (ps.executeUpdate() == 0)
        throw new RuntimeException("Object is not added");
    } catch (SQLException | IllegalAccessException e) {
      throw new RuntimeException(e);
    }
    System.out.println(query);
  }

  @Override
  public void delete(Long id) {
    String query = "DELETE FROM users WHERE id=?;";
    try (PreparedStatement ps = dataSource.getConnection().prepareStatement(query)) {
      ps.setLong(1, id);
      if (ps.executeUpdate() == 0)
        throw new RuntimeException("Object is not added");
    } catch (SQLException e) {
      e.fillInStackTrace();
    }
  }

  @Override
  public Optional<User> findByEmail(String email) {
    return Optional.empty();
  }

  public void createSchema() {
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

  private String getQueryDetails(Object obj, String status) {
    StringBuilder query = new StringBuilder();
    StringBuilder values = new StringBuilder();
    Field[] fields = obj.getClass().getDeclaredFields();
    OrmEntity ormEntity = obj.getClass().getAnnotation(OrmEntity.class);
    if (status.equals("save")) {
      query.append(String.format("INSERT INTO %s "
          + "(", ormEntity.table()));
      for (int i = 1; i < fields.length; ++i) {
        query.append(String.format("%s", fields[i].getName()));
        values.append("?");
        if (i != fields.length - 1) {
          query.append(",");
          values.append(",");
        }
      }
      query.append(") VALUES (").append(values).append(");");
    }
    else if (status.equals("update")) {
      query.append(String.format("UPDATE %s SET  ", ormEntity.table()));
      if (obj.getClass().getAnnotation(OrmColumnId.class) != null) {
        OrmColumnId ormColumnId = obj.getClass().getAnnotation(OrmColumnId.class);
        query.append(ormColumnId.id());
      }
      for (int i = 1; i < fields.length; ++i) {
        query.append(String.format("%s=?", fields[i].getName()));
        if (i != fields.length - 1) {
          query.append(",");
        }
      }
      query.append(" WHERE id=?;");
    }
    return query.toString();
  }
}
