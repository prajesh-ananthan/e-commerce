package io.ecommerce.service.impl;

import io.ecommerce.domain.Product;
import io.ecommerce.service.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Prajesh Ananthan
 *         Created on 3/9/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ProductServiceImplTest {

  ProductService productService;

  @Autowired
  public void setProductService(ProductService productService) {
    this.productService = productService;
  }

  @Test
  public void testListMethod() throws Exception {
    // Given

    // When
    List<Product> products = (List<Product>) productService.list();

    // Verify
    assertThat(products.size(), is(4));
  }
}