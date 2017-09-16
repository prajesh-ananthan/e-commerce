package io.ecommerce.domain;

import io.ecommerce.enums.OrderStatus;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Prajesh Ananthan
 *         Created on 5/8/2017.
 */
@Entity
@Table(name = "ORDER_HEADER")
public class Order extends AbstractDomain {

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "order", orphanRemoval = true)
  List<OrderDetail> orderDetails = new ArrayList<>();
  @OneToOne
  private Customer customer;
  @Embedded
  private Address shippingAddress;
  private OrderStatus orderStatus;

  public void addToOrderDetails(OrderDetail orderDetail) {
    orderDetail.setOrder(this);
    orderDetails.add(orderDetail);
  }

  public void removeOrderDetails(OrderDetail orderDetail) {
    if (orderDetail != null) {
      orderDetails.remove(orderDetail);
      orderDetail.setOrder(null);
    }
  }

  public List<OrderDetail> getOrderDetails() {
    return orderDetails;
  }

  public void setOrderDetails(List<OrderDetail> orderDetails) {
    this.orderDetails = orderDetails;
  }

  public Customer getCustomer() {
    return customer;
  }

  public void setCustomer(Customer customer) {
    this.customer = customer;
  }

  public Address getShippingAddress() {
    return shippingAddress;
  }

  public void setShippingAddress(Address shippingAddress) {
    this.shippingAddress = shippingAddress;
  }

  public OrderStatus getOrderStatus() {
    return orderStatus;
  }

  public void setOrderStatus(OrderStatus orderStatus) {
    this.orderStatus = orderStatus;
  }
}
