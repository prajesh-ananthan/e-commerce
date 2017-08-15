package io.prajesh.service.impl.mapservice;

import io.prajesh.domain.DomainObject;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author Prajesh Ananthan
 *         Created on 24/7/2017.
 */
public abstract class AbstractMapService {
  public static final String MAP_PROFILE = "map";
  protected Map<Integer, DomainObject> domainMap;

  protected AbstractMapService() throws IOException {
    loadDomainObjectsFromJSON();
  }

  protected List<DomainObject> listAll() {
    return new ArrayList<>(domainMap.values());
  }

  protected DomainObject getById(Integer id) {
    return domainMap.get(id);
  }

  protected DomainObject saveOrUpdate(DomainObject domainObject) {
    if (domainObject != null) {

      if (domainObject.getId() == null) {
        domainObject.setId(getNextKey());
      }
      domainMap.put(domainObject.getId(), domainObject);

      return domainObject;
    } else {
      throw new RuntimeException("Object Can't be null");
    }
  }

  protected void delete(Integer id) {
    domainMap.remove(id);
  }

  private Integer getNextKey() {
    if (!CollectionUtils.isEmpty(domainMap.keySet())) {
      return Collections.max(domainMap.keySet()) + 1;
    }
    return 1;
  }

  protected abstract void loadDomainObjectsFromJSON() throws IOException;
}
