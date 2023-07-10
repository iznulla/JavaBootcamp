package edu.school21.services;


import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;


import edu.school21.exceptions.AlreadyAuthenticatedException;
import edu.school21.models.User;
import edu.school21.repositories.UsersRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mockito;

import org.mockito.junit.jupiter.MockitoExtension;

//@ExtendWith(MockitoExtension.class)
public class UsersServiceImplTest {

  private UsersRepository usersRepository;
  UsersServiceImpl usersService;
  private User user;

  @BeforeEach
  void init() {
    usersRepository = Mockito.mock(UsersRepository.class);
    usersService = new UsersServiceImpl(usersRepository);
    user = new User(1L, "login", "Password123", false);
  }


  @Test
  void invalidLoginTest() {
    Mockito.when(usersRepository.findByLogin("merylpor")).thenReturn(user);
    assertThrows(AlreadyAuthenticatedException.class, () ->
        usersService.authenticate("merylpur", "Password123"));
    Mockito.verify(usersRepository).findByLogin(Mockito.anyString());
    Mockito.verify(usersRepository, Mockito.never()).update(user);
  }

  @Test
  void invalidPasswordTest() {
    Mockito.when(usersRepository.findByLogin("merylpor")).thenReturn(user);
    assertThrows(AlreadyAuthenticatedException.class, () ->
        usersService.authenticate("merylpor", "password"));
    Mockito.verify(usersRepository).findByLogin(Mockito.anyString());
    Mockito.verify(usersRepository, Mockito.never()).update(user);
  }

  @Test
  void accessAndUpdateTest() throws AlreadyAuthenticatedException {
    Mockito.when(usersRepository.findByLogin("merylpor")).thenReturn(user);
    assertTrue(usersService.authenticate("merylpor", "Password123"));
    Mockito.verify(usersRepository).findByLogin(Mockito.anyString());
    Mockito.verify(usersRepository).update(user);
  }

  @Test
  void alreadyAuthenticatedTest() throws AlreadyAuthenticatedException {
    Mockito.when(usersRepository.findByLogin("merylpor")).thenReturn(user);
    assertTrue(usersService.authenticate("merylpor", "Password123"));
    Mockito.verify(usersRepository).findByLogin(Mockito.anyString());
    Mockito.verify(usersRepository).update(user);
    assertThrows(AlreadyAuthenticatedException.class, () -> usersService.authenticate("merylpor", "Password123"));
  }

}
