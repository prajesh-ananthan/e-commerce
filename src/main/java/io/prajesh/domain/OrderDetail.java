package io.prajesh.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

/**
 * @author Prajesh Ananthan
 *         Created on 6/8/2017.
 */
@Data
@Entity
public class OrderDetail extends AbstractDomain {
  @OneToOne
  private Product product;
  private Integer quantity;
  @OneToOne
  private Order order;
}
