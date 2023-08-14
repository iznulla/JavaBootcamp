package school21.spring.service.application;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import school21.spring.service.JdbcManager.DriverManagerDs;
import school21.spring.service.JdbcManager.HikDataSource;
import school21.spring.service.models.User;
import school21.spring.service.repositories.UsersRepositoryJdbcImpl;
import school21.spring.service.repositories.UsersRepositoryJdbcTemplateImpl;

public class Main {

  public static void main(String[] args) {
    User user = new User("a@l.ru");
    ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
    UsersRepositoryJdbcTemplateImpl usersRepositoryJdbcTemplate = context.getBean("usersRepositoryJdbcTemplateImpl", UsersRepositoryJdbcTemplateImpl.class);
    System.out.println(usersRepositoryJdbcTemplate.findById(1L));
    List<User> userLst = usersRepositoryJdbcTemplate.findAll();

////    System.out.println(user);
////    DataSource dataSource = HikDataSource.getDs();
//    DataSource dManagerDs = DriverManagerDs.getDrMnDs();
//    UsersRepositoryJdbcTemplateImpl usersRepository = new UsersRepositoryJdbcTemplateImpl(dManagerDs);
////    User dd = usersRepository.findById(5L);
//    UsersRepositoryJdbcImpl usersRepository = new UsersRepositoryJdbcImpl(dManagerDs);
//    usersRepository.createSchema();
//    usersRepository.save(user);
//    User updateUser = usersRepository.findById(1L);
//    updateUser.setEmail("ab@list.ru");
//    usersRepository.update(updateUser);
//
////    User ss = usersRepository.findByEmail("ab@list.ru").orElse(null);
////    userList.forEach(System.out::println);
//    User user1 = new User("ss@gg.com");
//    usersRepository.save(user1);
//    List<User> userList = usersRepository.findAll();
    userLst.forEach(System.out::println);
//    User userUp = usersRepository.findById(3L);
//    System.out.println(userUp);
//    usersRepository.save(user);
  }
}