package io.prajesh.service.impl.dao;

import io.prajesh.domain.security.Role;
import io.prajesh.service.RoleService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * @author Prajesh Ananthan
 *         Created on 15/8/2017.
 */
@Service
@Profile(AbstractDaoService.JPA_DAO)
public class RoleServiceDao extends AbstractDaoService implements RoleService {

  @Override
  public List<?> list() {
    EntityManager em = emf.createEntityManager();
    return em.createQuery("from Role", Role.class).getResultList();
  }

  @Override
  public Role findById(Integer id) {
    EntityManager em = emf.createEntityManager();
    return em.find(Role.class, id);
  }

  @Override
  public Role saveOrUpdate(Role domainObject) {
    EntityManager em = emf.createEntityManager();
    em.getTransaction().begin();
    Role savedRole = em.merge(domainObject);
    em.getTransaction().commit();

    return savedRole;
  }

  @Override
  public void remove(Integer id) {
    EntityManager em = emf.createEntityManager();
    em.getTransaction().begin();
    Role deleteRole = em.find(Role.class, id);
    em.remove(deleteRole);
    em.getTransaction().commit();
  }
}
