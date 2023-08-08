package school21.spring.service.repositories;

import java.util.List;
import java.util.Optional;
import javax.sql.DataSource;
import school21.spring.service.models.User;


public class UsersRepositoryJdbcImpl implements UsersRepository{


  private final DataSource dataSource;

  public UsersRepositoryJdbcImpl(DataSource dataSource) {
    this.dataSource = dataSource;
  }

  @Override
  public User findById(Long id) {
    return null;
  }

  @Override
  public List<User> findAll() {
    return null;
  }

  @Override
  public void save(User entity) {

  }

  @Override
  public void update(User entity) {

  }

  @Override
  public void delete(Long id) {

  }

  @Override
  public Optional<User> findByEmail(String email) {
    return Optional.empty();
  }
//
//  @Override
//  public User findById(Long id) {
//    return null;
//  }
//
//  @Override
//  public List<User> findAll() {
//    return null;
//  }
//
//  @Override
//  public void save(User entity) {
//
//  }
//
//  @Override
//  public void update(User entity) {
//
//  }
//
//  @Override
//  public void delete(Long id) {
//
//  }
//
//  @Override
//  public Optional<User> findByEmail(String email) {
//    return Optional.empty();
//  }
}
