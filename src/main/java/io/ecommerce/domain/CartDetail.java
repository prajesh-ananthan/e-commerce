package io.ecommerce.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author Prajesh Ananthan
 *         Created on 1/8/2017.
 */
@Entity
@Table(name = "cart_detail")
public class CartDetail extends AbstractDomain {

  @ManyToOne
  private Cart cart;
  private Integer quantity;
  @OneToOne
  private Product product;

  public Cart getCart() {
    return cart;
  }

  public void setCart(Cart cart) {
    this.cart = cart;
  }

  public Integer getQuantity() {
    return quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }

  public Product getProduct() {
    return product;
  }

  public void setProduct(Product product) {
    this.product = product;
  }
}
