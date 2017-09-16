package io.ecommerce.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Prajesh Ananthan
 *         Created on 6/8/2017.
 */

@MappedSuperclass
public class AbstractDomain implements DomainObject {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;
  @Version
  private Integer version;
  private Date createdDate;
  private Date modifiedDate;

  public Integer getVersion() {
    return version;
  }

  public void setVersion(Integer version) {
    this.version = version;
  }

  public Date getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(Date createdDate) {
    this.createdDate = createdDate;
  }

  public Date getModifiedDate() {
    return modifiedDate;
  }

  public void setModifiedDate(Date modifiedDate) {
    this.modifiedDate = modifiedDate;
  }

  @Override
  public Integer getId() {
    return id;
  }

  @Override
  public void setId(Integer id) {
    this.id = id;
  }

  @PreUpdate
  @PrePersist
  public void updateTimestamps() {
    modifiedDate = new Date();
    if (createdDate == null) {
      createdDate = new Date();
    }
  }
}
