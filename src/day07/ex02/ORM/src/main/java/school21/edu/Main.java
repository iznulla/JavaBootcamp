package school21.edu;

import school21.edu.models.User;
import school21.edu.ormmanager.OrmManager;
import school21.edu.ormmanager.OrmManagerImpl;

public class Main {

  public static void main(String[] args) {
    User user = new User( "Rinat", "Klinkov", 31);
    User user1 = new User();
    OrmManager ormManager = new OrmManagerImpl();
    ormManager.save(user);
    ormManager.save(user1);
    User us = ormManager.fidById(1L, User.class);
    System.out.println(us.getFirstName());
    System.out.println("Hello world!");
  }
}