package io.ecommerce.domain;

import javax.persistence.*;

/**
 * @author Prajesh Ananthan
 *         Created on 23/7/2017.
 */
@Entity
@Table(name = "customer")
public class Customer extends AbstractDomain {

  private String firstName;
  private String lastName;
  private String email;
  private String phoneNumber;

  @Embedded
  private Address billingAddress;

  @OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
  private User user;

//  @Embedded
//  private Address shippingAddress;
//
//  public Address getShippingAddress() {
//    return shippingAddress;
//  }
//
//  public void setShippingAddress(Address shippingAddress) {
//    this.shippingAddress = shippingAddress;
//  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public Address getBillingAddress() {
    return billingAddress;
  }

  public void setBillingAddress(Address billingAddress) {
    this.billingAddress = billingAddress;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }
}