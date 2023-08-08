package school21.spring.service;
import school21.spring.service.models.User;

public class Main {

  public static void main(String[] args) {
//    InitDb.init();
    User user = new User();
//    JdbcUtils.preStatement()
    user.setIdentifier(1L);
    System.out.println(user);
  }
}