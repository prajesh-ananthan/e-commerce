package io.ecommerce.domain;

import javax.persistence.Entity;
import java.math.BigDecimal;

/**
 * @author Prajesh Ananthan
 *         Created on 21/7/2017.
 */
@Entity
public class Product extends AbstractDomain {
  private String description;
  private BigDecimal price;
  private String imageUrl;

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }
}