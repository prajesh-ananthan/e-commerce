package io.prajesh.service.impl.dao;

import io.prajesh.domain.Order;
import io.prajesh.service.OrderService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * @author Prajesh Ananthan
 *         Created on 6/8/2017.
 */
@Service
@Profile(AbstractDaoService.JPA_DAO)
public class OrderServiceDao extends AbstractDaoService implements OrderService {

  @Override
  public List<?> list() {
    EntityManager em = emf.createEntityManager();
    return em.createQuery("from Order", Order.class).getResultList();
  }

  @Override
  public Order findById(Integer id) {
    EntityManager em = emf.createEntityManager();
    return em.find(Order.class, id);
  }

  @Override
  public Order saveOrUpdate(Order domainObject) {
    EntityManager em = emf.createEntityManager();
    em.getTransaction().begin();
    Order savedOrder = em.merge(domainObject);
    em.getTransaction().commit();

    return savedOrder;
  }

  @Override
  public void remove(Integer id) {
    EntityManager em = emf.createEntityManager();
    em.getTransaction().begin();
    Order deleteOrder = em.find(Order.class, id);
    em.remove(deleteOrder);
    em.getTransaction().commit();
  }
}
