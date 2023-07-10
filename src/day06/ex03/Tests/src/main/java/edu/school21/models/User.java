package edu.school21.models;

import java.util.Objects;

public class User {
  private Long id;
  private String login;
  private String password;
  private boolean isStatusAuth = false;

  public User(Long id, String login, String password, boolean isStatusAuth) {
    this.id = id;
    this.login = login;
    this.password = password;
    this.isStatusAuth = isStatusAuth;
  }

  public Long getId() {
    return id;
  }

  public String getLogin() {
    return login;
  }

  public String getPassword() {
    return password;
  }

  public boolean getStatusAuth() {
    return isStatusAuth;
  }

  public void setStatusAuth(boolean auth) {
    isStatusAuth = auth;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || this.getClass() != o.getClass()) return false;
    User user = (User) o;
    return Objects.equals(this.id, user.id) &&
        Objects.equals(this.login, user.login) &&
        Objects.equals(this.password, user.password) &&
        Objects.equals(this.isStatusAuth, user.isStatusAuth);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, login, password, isStatusAuth);
  }

  @Override
  public String toString() {
    return "ID=" + id +
        "\nlogin=" + login +
        "\npassword=" + password +
        "\nisStatusAuth=" + isStatusAuth;
  }
}
