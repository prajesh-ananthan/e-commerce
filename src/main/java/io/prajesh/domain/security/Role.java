package io.prajesh.domain.security;

import io.prajesh.domain.AbstractDomain;
import io.prajesh.domain.User;

import javax.persistence.Entity;
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

  // A single role can have many users
  // A single user can have many roles
  @ManyToMany
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
