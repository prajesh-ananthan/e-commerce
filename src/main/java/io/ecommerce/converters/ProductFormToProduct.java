package io.ecommerce.converters;

import io.ecommerce.commands.ProductForm;
import io.ecommerce.domain.Product;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * @author Prajesh Ananthan
 *         Created on 27/8/2017.
 */
@Component
public class ProductFormToProduct implements Converter<ProductForm, Product> {

  @Override
  public Product convert(ProductForm productForm) {
    Product product = new Product();
    product.setId(productForm.getId());
    product.setDescription(productForm.getDescription());
    product.setImageUrl(productForm.getImageUrl());
    product.setPrice(productForm.getPrice());
    return product;
  }
}
