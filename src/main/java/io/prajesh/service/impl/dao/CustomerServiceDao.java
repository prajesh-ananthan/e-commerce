package io.prajesh.service.impl.dao;

import io.prajesh.domain.Customer;
import io.prajesh.service.CustomerService;
import io.prajesh.service.security.EncryptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by ANAN011 on 27/7/2017.
 *
 * @author Prajesh Ananthan, arvato Systems Malaysia Sdn Bhd
 */
@Service
@Profile(AbstractDaoService.JPA_DAO)
public class CustomerServiceDao extends AbstractDaoService implements CustomerService {

  EncryptionService encryptionService;

  @Autowired
  public void setEncryptionService(EncryptionService encryptionService) {
    this.encryptionService = encryptionService;
  }

  @Override
  public List<?> list() {
    EntityManager em = emf.createEntityManager();
    return em.createQuery("from Customer", Customer.class).getResultList();
  }

  @Override
  public Customer findById(Integer id) {
    EntityManager em = emf.createEntityManager();
    return em.find(Customer.class, id);
  }

  @Override
  public Customer saveOrUpdate(Customer domainObject) {
    EntityManager em = emf.createEntityManager();
    em.getTransaction().begin();

    if (domainObject.getUser() != null && domainObject.getUser().getPassword() != null) {
      String encryptedPassword = encryptionService.encryptString(domainObject.getUser().getPassword());
      domainObject.getUser().setEncryptedPassword(encryptedPassword);
    }

    Customer savedCustomer = em.merge(domainObject);
    em.getTransaction().commit();

    return savedCustomer;
  }

  @Override
  public void remove(Integer id) {
    EntityManager em = emf.createEntityManager();
    em.getTransaction().begin();
    Customer deleteCustomer = em.find(Customer.class, id);
    em.remove(deleteCustomer);
    em.getTransaction().commit();
  }
}
