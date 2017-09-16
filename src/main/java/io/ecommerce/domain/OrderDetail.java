package io.ecommerce.domain;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

/**
 * @author Prajesh Ananthan
 *         Created on 6/8/2017.
 */
@Entity
public class OrderDetail extends AbstractDomain {
  @OneToOne
  private Product product;
  private Integer quantity;
  @OneToOne
  private Order order;

  public Product getProduct() {
    return product;
  }

  public void setProduct(Product product) {
    this.product = product;
  }

  public Integer getQuantity() {
    return quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }

  public Order getOrder() {
    return order;
  }

  public void setOrder(Order order) {
    this.order = order;
  }
}
