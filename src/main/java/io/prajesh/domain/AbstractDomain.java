package io.prajesh.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Prajesh Ananthan
 *         Created on 6/8/2017.
 */

@Data
@MappedSuperclass
public class AbstractDomain implements DomainObject {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;
  @Version
  private Integer version;
  private Date createdDate;
  private Date modifiedDate;

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
