package edu.school21.repositories;

import static org.junit.jupiter.api.Assertions.*;
import edu.school21.models.Product;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.sql.DataSource;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

public class ProductsRepositoryJdbcImplTest {

  DataSource dataSource;
  final List<Product> EXPECTED_FIND_ALL_PRODUCTS = new ArrayList<>(Arrays.asList(
      new Product(0L, "iphone13", 1000),
      new Product(1L, "iphone14", 1100),
      new Product(2L, "iphone14Pro", 1245.2),
      new Product(3L,"iphone14ProMax", 1350.3),
      new Product(4L,"iphone15", 1400),
      new Product(5L, "iphone15Pro", 1450),
      new Product(6L, "iphone15ProMax", 1500),
      new Product(7L, "ilnaz", 2000)
  ));
  ProductRepositoryJdbcImpl products;

  @BeforeEach
  void init() throws SQLException {
    // создаем базу данных в памяти
    dataSource = new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.HSQL)
        // заполняем базу данных с помощю скриптов sql
        .addScript("/schema.sql")
        .addScript("/data.sql").build();
    products = new ProductRepositoryJdbcImpl(dataSource);
  }

  @AfterEach
  void shutDownData() {
    if (dataSource != null)
      ((EmbeddedDatabase) dataSource).shutdown();
  }

  @Test
  void findAll() {
    assertEquals(EXPECTED_FIND_ALL_PRODUCTS, products.findAll());
  }
}
