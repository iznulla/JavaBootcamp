package edu.school21.chat.repositories;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;


public class HikDataSource {
  private static final HikariConfig config = new HikariConfig();
  private static final HikariDataSource ds;


  static {
    config.setJdbcUrl("jdbc:postgresql://localhost:5432/chatdb");
    config.setUsername("codela");
    config.setPassword("1");
    config.setMaximumPoolSize(4);
    ds = new HikariDataSource(config);
  }
  private HikDataSource() {}


  public static HikariDataSource getHkDataSource() {
    return ds;
  }
}
