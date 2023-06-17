package edu.school21.chat.repositories;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class HikDataSource {
  private static final HikariConfig config = new HikariConfig();
  private static final HikariDataSource ds;

  static {
    config.setJdbcUrl("jdbc:postgresql://localhost:5432/chatdb");
    config.setUsername("postgres");
    config.setPassword("1");
    config.setMaximumPoolSize(4);
    ds = new HikariDataSource(config);
  }
  private HikDataSource() {}

  public static Connection getConnection() throws SQLException {
    return ds.getConnection();
  }

  public static HikariDataSource getHkDataSource() {
    return ds;
  }
}
