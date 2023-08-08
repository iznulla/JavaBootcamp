package school21.spring.service;
import com.zaxxer.hikari.HikariDataSource;
import javax.sql.DataSource;
import school21.spring.service.JdbcManager.HikDataSource;
import school21.spring.service.models.User;
import school21.spring.service.repositories.UsersRepository;
import school21.spring.service.repositories.UsersRepositoryJdbcImpl;

public class Main {

  public static void main(String[] args) {
//    InitDb.init();
    User user = new User("Inzn");
////    JdbcUtils.preStatement()
//    user.setIdentifier(1L);
    System.out.println(user);
    DataSource dataSource = HikDataSource.getDs();
    UsersRepositoryJdbcImpl usersRepository = new UsersRepositoryJdbcImpl(dataSource);
    usersRepository.createSchema();
    usersRepository.save(user);
    user.setEmail("Laho");
//    usersRepository.update(user);
//    usersRepository.delete(2L);
    usersRepository.findById(5L);
    System.out.println(user);
  }
}