package io.ecommerce.service.impl;

import io.ecommerce.domain.Order;
import io.ecommerce.repositories.OrderRespository;
import io.ecommerce.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Prajesh Ananthan
 *         Created on 17/8/2017.
 */
@Service
public class OrderServiceImpl implements OrderService {

  private OrderRespository orderRespository;

  @Autowired
  public void setOrderRespository(OrderRespository orderRespository) {
    this.orderRespository = orderRespository;
  }

  @Override
  public List<?> list() {
    List<Order> orders = new ArrayList<>();
    orderRespository.findAll().forEach(orders::add);
    return orders;
  }

  @Override
  public Order findById(Integer id) {
    return orderRespository.findOne(id);
  }

  @Override
  public Order saveOrUpdate(Order domainObject) {
    return orderRespository.save(domainObject);
  }

  @Override
  public void remove(Integer id) {
    orderRespository.delete(id);
  }
}
