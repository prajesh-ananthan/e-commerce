package io.prajesh.domain;

import io.prajesh.domain.security.Role;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Prajesh Ananthan
 *         Created on 29/7/2017.
 */
@Entity
public class User extends AbstractDomain {


  @ManyToMany
  @JoinTable
  private List<Role> roles = new ArrayList<>();
  private String userName;
  @Transient
  private String password;
  private String encryptedPassword;
  private Boolean enabled = true;
  @OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
  private Customer customer;
  // cart is removed when user is deleted
  @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
  private Cart cart;

  public List<Role> getRoles() {
    return roles;
  }

  public void setRoles(List<Role> roles) {
    this.roles = roles;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getEncryptedPassword() {
    return encryptedPassword;
  }

  public void setEncryptedPassword(String encryptedPassword) {
    this.encryptedPassword = encryptedPassword;
  }

  public Boolean getEnabled() {
    return enabled;
  }

  public void setEnabled(Boolean enabled) {
    this.enabled = enabled;
  }

  public Cart getCart() {
    return cart;
  }

  public void setCart(Cart cart) {
    this.cart = cart;
    cart.setUser(this);
  }

  public Customer getCustomer() {
    return customer;
  }

  // Truly what makes the User and Customer bi-directional
  public void setCustomer(Customer customer) {
    this.customer = customer;
    customer.setUser(this);
  }

  public void addRole(Role role) {
    if (!roles.contains(role)) {
      roles.add(role);
    }
    if (!role.getUsers().contains(this)) {
      role.getUsers().add(this);
    }
  }

  public void removeRole(Role role) {
    this.roles.remove(role);
    role.getUsers().remove(this);
  }
}
