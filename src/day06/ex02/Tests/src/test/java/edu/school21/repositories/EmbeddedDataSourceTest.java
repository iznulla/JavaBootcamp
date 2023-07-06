package edu.school21.repositories;

import static org.junit.jupiter.api.Assertions.*;


import java.sql.SQLException;
import javax.sql.DataSource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

public class EmbeddedDataSourceTest {

  private DataSource dataSource;


  @BeforeEach
  void init() {
    dataSource = new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.HSQL)
        .addScript("/schema.sql")
        .addScript("/data.sql").build();
  }

  @Test
  void dataSourceTest() throws SQLException {
    assertNotNull(dataSource.getConnection());
  }
}
