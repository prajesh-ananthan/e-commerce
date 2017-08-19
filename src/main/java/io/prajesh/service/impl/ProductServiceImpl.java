package io.prajesh.service.impl;

import io.prajesh.domain.Product;
import io.prajesh.repositories.ProductRepository;
import io.prajesh.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Prajesh Ananthan
 *         Created on 16/8/2017.
 */
@Service
public class ProductServiceImpl implements ProductService {

  private ProductRepository productRepository;

  @Autowired
  public void setProductRepository(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  @Override
  public List<?> list() {
    List<Product> products = new ArrayList<>();
    productRepository.findAll().forEach(products::add);
    return products;
  }

  @Override
  public Product findById(Integer id) {
    return productRepository.findOne(id);
  }

  @Override
  public Product saveOrUpdate(Product domainObject) {
    return productRepository.save(domainObject);
  }

  @Override
  public void remove(Integer id) {
    productRepository.delete(id);
  }
}