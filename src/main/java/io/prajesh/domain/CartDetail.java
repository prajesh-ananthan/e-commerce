package io.prajesh.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 * @author Prajesh Ananthan
 *         Created on 1/8/2017.
 */
@Data
@Entity
public class CartDetail extends AbstractDomain {

  @ManyToOne
  private Cart cart;
  private Integer quantity;
  @OneToOne
  private Product product;
}
