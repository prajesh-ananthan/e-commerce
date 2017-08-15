package io.prajesh.service;

import io.prajesh.domain.Product;
import org.springframework.stereotype.Service;

/**
 * @author Prajesh Ananthan
 *         Created on 21/7/2017.
 */
@Service
public interface ProductService extends CRUDService<Product> {
  String PRODUCTS_JSON_FILE = "json/products.json";
}