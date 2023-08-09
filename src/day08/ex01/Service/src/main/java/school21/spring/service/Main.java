package school21.spring.service;
import com.zaxxer.hikari.HikariDataSource;
import java.util.List;
import javax.sql.DataSource;
import school21.spring.service.JdbcManager.HikDataSource;
import school21.spring.service.models.User;
import school21.spring.service.repositories.UsersRepository;
import school21.spring.service.repositories.UsersRepositoryJdbcImpl;

public class Main {

  public static void main(String[] args) {
    User user = new User("a@l.ru");
    System.out.println(user);
    DataSource dataSource = HikDataSource.getDs();
    UsersRepositoryJdbcImpl usersRepository = new UsersRepositoryJdbcImpl(dataSource);
    usersRepository.createSchema();
    usersRepository.save(user);
  }
}