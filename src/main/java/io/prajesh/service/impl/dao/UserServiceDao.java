package io.prajesh.service.impl.dao;

import io.prajesh.domain.User;
import io.prajesh.service.UserService;
import io.prajesh.service.security.EncryptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * @author Prajesh Ananthan
 *         Created on 30/7/2017.
 */
@Service
@Profile(AbstractDaoService.JPA_DAO)
public class UserServiceDao extends AbstractDaoService implements UserService {

  private EncryptionService encryptionService;

  @Autowired
  public void setEncryptionService(EncryptionService encryptionService) {
    this.encryptionService = encryptionService;
  }

  @Override
  public List<?> list() {
    EntityManager em = emf.createEntityManager();
    return em.createQuery("from User", User.class).getResultList();
  }

  @Override
  public User findById(Integer id) {
    EntityManager em = emf.createEntityManager();
    return em.find(User.class, id);
  }

  @Override
  public User saveOrUpdate(User domainObject) {
    if (domainObject.getPassword() != null) {
      domainObject.setEncryptedPassword(encryptionService.encryptString(domainObject.getPassword()));
    }

    EntityManager em = emf.createEntityManager();
    em.getTransaction().begin();
    User savedUser = em.merge(domainObject);
    em.getTransaction().commit();

    return savedUser;
  }

  @Override
  public void remove(Integer id) {
    EntityManager em = emf.createEntityManager();
    em.getTransaction().begin();
    User user = em.find(User.class, id);
    em.remove(user);
    em.getTransaction().commit();
  }
}
