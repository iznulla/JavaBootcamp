package school21.edu;

import school21.edu.models.User;
import school21.edu.ormmanager.OrmManager;
import school21.edu.ormmanager.OrmManagerImpl;

public class Main {

  public static void main(String[] args) {
    User user = new User(1L, "Rinat", "Klinkov", 31);
    OrmManager ormManager = new OrmManagerImpl();
    ormManager.save(user);
    System.out.println("Hello world!");
  }
}