package io.ecommerce.service.impl;

import io.ecommerce.commands.ProductForm;
import io.ecommerce.converters.ProductFormToProduct;
import io.ecommerce.domain.Product;
import io.ecommerce.repositories.ProductRepository;
import io.ecommerce.service.ProductService;
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
  private ProductFormToProduct productFormToProduct;

  @Autowired
  public void setProductRepository(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  @Autowired
  public void setProductFormToProduct(ProductFormToProduct productFormToProduct) {
    this.productFormToProduct = productFormToProduct;
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

  @Override
  public Product saveOrUpdateProductForm(ProductForm productForm) {
    Product product = productFormToProduct.convert(productForm);
    return productRepository.save(product);
  }
}
