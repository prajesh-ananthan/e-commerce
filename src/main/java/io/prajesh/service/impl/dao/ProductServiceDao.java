package io.prajesh.service.impl.dao;

import io.prajesh.domain.Product;
import io.prajesh.service.ProductService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * @author Prajesh Ananthan
 *         Created on 27/7/2017.
 */
@Service
@Profile(AbstractDaoService.JPA_DAO)
public class ProductServiceDao extends AbstractDaoService implements ProductService {

  @Override
  public List<Product> list() {
    EntityManager em = emf.createEntityManager();
    return em.createQuery("from Product", Product.class).getResultList();
  }

  @Override
  public Product findById(Integer id) {
    EntityManager em = emf.createEntityManager();
    return em.find(Product.class, id);
  }

  @Override
  public Product saveOrUpdate(Product domainObject) {
    EntityManager em = emf.createEntityManager();
    em.getTransaction().begin();
    Product savedProduct = em.merge(domainObject);
    em.getTransaction().commit();

    return savedProduct;
  }

  @Override
  public void remove(Integer id) {
    EntityManager em = emf.createEntityManager();
    em.getTransaction().begin();
    Product deleteProduct = em.find(Product.class, id);
    em.remove(deleteProduct);
    em.getTransaction().commit();
  }
}
