package io.prajesh.domain;

import io.prajesh.enums.OrderStatus;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Prajesh Ananthan
 *         Created on 5/8/2017.
 */
@Data
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

  public void setOrderStatus(OrderStatus orderStatus) {
    this.orderStatus = orderStatus;
  }

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
}
