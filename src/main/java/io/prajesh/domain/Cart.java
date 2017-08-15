package io.prajesh.domain;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Prajesh Ananthan
 *         Created on 1/8/2017.
 */

@Entity
@Data
public class Cart extends AbstractDomain {

  @OneToOne
  private User user;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "cart", orphanRemoval = true)
  private List<CartDetail> cartDetails = new ArrayList<>();

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public List<CartDetail> getCartDetails() {
    return cartDetails;
  }

  public void setCartDetails(List<CartDetail> cartDetails) {
    this.cartDetails = cartDetails;
  }

  public void addCartDetail(CartDetail cartDetail) {
    cartDetails.add(cartDetail);
    cartDetail.setCart(this);
  }

  public void removeCartDetail(CartDetail cartDetail) {
    if (cartDetail != null) {
      cartDetails.remove(cartDetail);
      cartDetail.setCart(null);
    }
  }
}
