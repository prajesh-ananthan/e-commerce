package io.ecommerce.domain.security;

import io.ecommerce.domain.AbstractDomain;
import io.ecommerce.domain.User;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Prajesh Ananthan
 *         Created on 14/8/2017.
 */
@Entity
public class Role extends AbstractDomain {

  public static String ADMIN = "ADMIN";
  public static String CUSTOMER = "CUSTOMER";

  // A single role can have many users
  // A single user can have many roles
  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable
  private List<User> users = new ArrayList<>();
  private String role;

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }

  public List<User> getUsers() {
    return users;
  }

  public void setUsers(List<User> users) {
    this.users = users;
  }

  public void addUser(User user) {
    if (!users.contains(user)) {
      users.add(user);
    }

    if (!user.getRoles().contains(this)) {
      user.getRoles().add(this);
    }
  }

  public void removeUser(User user) {
    users.remove(user);
    user.getRoles().remove(this);
  }
}
