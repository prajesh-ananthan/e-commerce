package io.ecommerce.service;

import java.util.List;

/**
 * @author Prajesh Ananthan
 *         Created on 24/7/2017.
 */
public interface CRUDService<T> {

  List<?> list();

  T findById(Integer id);

  T saveOrUpdate(T domainObject);

  void remove(Integer id);
}