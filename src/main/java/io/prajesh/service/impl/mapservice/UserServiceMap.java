package io.prajesh.service.impl.mapservice;

import io.prajesh.domain.DomainObject;
import io.prajesh.domain.User;
import io.prajesh.service.UserService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * @author Prajesh Ananthan
 *         Created on 30/7/2017.
 */
@Service
@Profile(AbstractMapService.MAP_PROFILE)
public class UserServiceMap extends AbstractMapService implements UserService {

  protected UserServiceMap() throws IOException {
  }

  @Override
  public List<?> list() {
    return super.listAll();
  }

  @Override
  public User findById(Integer id) {
    return (User) super.getById(id);
  }

  @Override
  public User saveOrUpdate(User domainObject) {
    return (User) super.saveOrUpdate((DomainObject) domainObject);
  }

  @Override
  public void remove(Integer id) {
    super.delete(id);
  }

  @Override
  protected void loadDomainObjectsFromJSON() throws IOException {

  }
}
