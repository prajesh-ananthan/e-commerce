package io.prajesh.domain;

import lombok.Data;

import javax.persistence.Entity;
import java.math.BigDecimal;

/**
 * @author Prajesh Ananthan
 *         Created on 21/7/2017.
 */
@Data
@Entity
public class Product extends AbstractDomain {
  private String description;
  private BigDecimal price;
  private String imageUrl;
}