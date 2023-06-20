package edu.school21.chat.app;


import edu.school21.chat.models.User;
import edu.school21.chat.repositories.HikDataSource;
import edu.school21.chat.repositories.UserRepositories;
import edu.school21.chat.repositories.UserRepositoriesJdbcImpl;
import java.sql.SQLException;
import java.util.List;

public class Program {

  public static void main(String[] args) throws SQLException {
    UserRepositories usr = new UserRepositoriesJdbcImpl(HikDataSource.getHkDataSource());
    List<User> users = usr.findAll(1, 10);
    users.forEach(System.out::println);

  }
}
