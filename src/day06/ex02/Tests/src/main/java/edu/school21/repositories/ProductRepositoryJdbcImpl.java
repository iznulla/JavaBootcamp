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

public class ProductRepositoryJdbcImpl implements ProductsRepository {

  Connection connection;

  public ProductRepositoryJdbcImpl(DataSource dataSource) throws SQLException {
    connection = dataSource.getConnection();
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
    return Optional.empty();
  }

  @Override
  public void update(Product product) {

  }

  @Override
  public void save(Product product) {

  }

  @Override
  public void delete(Long id) {

  }
}
