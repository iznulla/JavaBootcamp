package edu.school21.services;

import edu.school21.exceptions.AlreadyAuthenticatedException;
import edu.school21.models.User;
import edu.school21.repositories.UsersRepository;
import java.util.Optional;

public class UsersServiceImpl {
  private final UsersRepository usersRepository;

  public UsersServiceImpl(UsersRepository usersRepository) {
    this.usersRepository = usersRepository;
  }

  boolean authenticate(String login, String password) throws AlreadyAuthenticatedException {
    Optional<User> user = Optional.ofNullable(usersRepository.findByLogin(login));
    if (!user.isPresent())
      throw new AlreadyAuthenticatedException("User not found");
    if (user.get().getStatusAuth())
      throw new AlreadyAuthenticatedException("user is authenticated");
    if (user.get().getPassword().equals(password)) {
      user.get().setStatusAuth(true);
      usersRepository.update(user.get());
    } else {
      throw new AlreadyAuthenticatedException("invalid password");
    }
    return user.get().getStatusAuth();
  }
}
