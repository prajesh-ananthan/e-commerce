package io.prajesh.service.impl.dao;

import io.prajesh.domain.Product;
import io.prajesh.service.ProductService;
import io.prajesh.service.impl.mapservice.AbstractMapService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by ANAN011 on 27/7/2017.
 *
 * @author Prajesh Ananthan, arvato Systems Malaysia Sdn Bhd
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ActiveProfiles(AbstractMapService.MAP_PROFILE)
public class ProductServiceDaoTest {

  private ProductService productService;

  @Autowired
  public void setProductService(ProductService productService) {
    this.productService = productService;
  }

  @Test
  public void testList() throws Exception {
    List<Product> products = (List<Product>) productService.list();
    assertEquals(products.size(), 4);
  }
}