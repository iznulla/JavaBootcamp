package edu.school21.repositories;

import edu.school21.models.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;

public class ProductRepositoryJdbcImpl implements ProductsRepository {

  Connection connection;
  private final JdbcTemplate jdbcTemplateObject;

  public ProductRepositoryJdbcImpl(DataSource dataSource) throws SQLException {
    connection = dataSource.getConnection();
    jdbcTemplateObject = new JdbcTemplate(dataSource);
  }

  @Override
  public List<Product> findAll() {
    List<Product> products = new ArrayList<>();
    String sql = "SELECT * FROM products";
    try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
      ResultSet resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        Product product = new Product(resultSet.getLong("ID"),
            resultSet.getString("name"),
                resultSet.getDouble("price"));
        products.add(product);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return products;
  }

  @Override
  public Optional<Product> findById(Long id) {
    String sql = "SELECT * FROM products WHERE products.id = ?";
    try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
      preparedStatement.setLong(1, id);
      ResultSet resultSet = preparedStatement.executeQuery();
      if (resultSet.next()) {
        return Optional.of(new Product(resultSet.getLong("ID"),
            resultSet.getString("name"), resultSet.getDouble("price")));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return Optional.empty();
  }

  @Override
  public void update(Product product) {
    String sql = "UPDATE products set name=?, price=? WHERE id=?";
    jdbcTemplateObject.update(sql, product.getName(), product.getPrice(), product.getID());
  }

  @Override
  public void save(Product product) {
    String sql = "INSERT INTO products VALUES(?, ?, ?)";
    jdbcTemplateObject.update(sql, product.getID(), product.getName(), product.getPrice());
  }

  @Override
  public void delete(Long id) {
    String sql = "DELETE FROM products WHERE id=?";
    jdbcTemplateObject.update(sql, id);
  }
}
