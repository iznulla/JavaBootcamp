package school21.edu;

import school21.edu.models.Computer;
import school21.edu.models.User;
import school21.edu.ormmanager.OrmManager;
import school21.edu.ormmanager.OrmManagerImpl;

public class Main {

  public static void main(String[] args) {
    User user = new User( "Rinat", "Klinkov", 31);
    Computer computer = new Computer("Apple", 3);
    OrmManager ormManager = new OrmManagerImpl();
    ormManager.save(user);
    ormManager.save(computer);
    User us = ormManager.fidById(1L, User.class);
    us.setAge(null);
    ormManager.update(us);
    us.setAge(33);
    Computer co = ormManager.fidById(1L, Computer.class);
    System.out.println(us.getId());
    ormManager.update(us);
    System.out.println(co.getBrandName());
  }
}