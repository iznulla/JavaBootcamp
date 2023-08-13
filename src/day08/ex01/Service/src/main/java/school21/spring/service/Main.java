package school21.spring.service;
import java.util.List;
import javax.sql.DataSource;
import school21.spring.service.JdbcManager.HikDataSource;
import school21.spring.service.models.User;
import school21.spring.service.repositories.UsersRepositoryJdbcTemplateImpl;

public class Main {

  public static void main(String[] args) {
    User user = new User("a@l.ru");
//    System.out.println(user);
    DataSource dataSource = HikDataSource.getDs();
    UsersRepositoryJdbcTemplateImpl usersRepository = new UsersRepositoryJdbcTemplateImpl(dataSource);
//    User dd = usersRepository.findById(5L);
//    usersRepository.save(user);
    User updateUser = usersRepository.findById(67L);
    updateUser.setEmail("ab@list.ru");
//    usersRepository.delete(66L);
    usersRepository.update(updateUser);
    List<User> userList = usersRepository.findAll();
    User ss = usersRepository.findByEmail("ab@list.ru").orElse(null);
//    userList.forEach(System.out::println);
    System.out.println(ss);
//    usersRepository.save(user);
  }
}