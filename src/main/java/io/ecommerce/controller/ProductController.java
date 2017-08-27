package io.ecommerce.controller;

import com.google.common.annotations.VisibleForTesting;
import io.ecommerce.commands.ProductForm;
import io.ecommerce.converters.ProductToProductForm;
import io.ecommerce.domain.Product;
import io.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

/**
 * @author Prajesh Ananthan
 *         Created on 21/7/2017.
 */

@Controller
@RequestMapping("/product")
public class ProductController {

  @VisibleForTesting
  static final String ERROR_PAGE = "error/404";
  @VisibleForTesting
  static final String PRODUCT = "product";
  @VisibleForTesting
  static final String PRODUCTS = "products";
  @VisibleForTesting
  static final String PRODUCT_PAGE = PRODUCT + "/" + PRODUCT;
  @VisibleForTesting
  static final String PRODUCTS_PAGE = PRODUCT + "/" + PRODUCTS;
  @VisibleForTesting
  static final String REDIRECT_PRODUCT_LIST = "redirect:/" + PRODUCT + "/list";
  @VisibleForTesting
  static final String REDIRECT_PRODUCT_PAGE = "redirect:/" + PRODUCT + "/";

  private static final String PRODUCT_FORM_PAGE = PRODUCT + "/product-form";
  private static final String PRODUCT_FORM = "productForm";
  private ProductService productService;
  private ProductToProductForm productToProductForm;

  @Autowired
  public void setProductService(ProductService productService) {
    this.productService = productService;
  }

  @Autowired
  public void setProductToProductForm(ProductToProductForm productToProductForm) {
    this.productToProductForm = productToProductForm;
  }

  @GetMapping("/list")
  public String list(Model model) {
    model.addAttribute(PRODUCTS, productService.list());
    return PRODUCTS_PAGE;
  }

  @GetMapping("/{id}")
  public String findById(@PathVariable Integer id, Model model) {
    final Product product = productService.findById(id);
    if (product != null) {
      model.addAttribute(PRODUCT, productToProductForm.convert(product));
      return PRODUCT_PAGE;
    }
    return ERROR_PAGE;
  }

  @GetMapping("/new")
  public String create(Model model) {
    model.addAttribute(PRODUCT_FORM, new Product());
    return PRODUCT_FORM_PAGE;
  }

  @GetMapping("/edit/{id}")
  public String edit(@PathVariable Integer id, Model model) {
    final Product product = productService.findById(id);
    model.addAttribute(PRODUCT_FORM, productToProductForm.convert(product));
    return PRODUCT_FORM_PAGE;
  }

  @GetMapping(value = "/remove/{id}")
  public String delete(@PathVariable Integer id, Model model) {
    productService.remove(id);
    return REDIRECT_PRODUCT_LIST;
  }

  @PostMapping
  public String saveOrUpdate(@Valid ProductForm productForm, BindingResult bindingResult) {

    if (bindingResult.hasErrors()) {
      return PRODUCT_FORM_PAGE;
    }

    Product savedProduct = productService.saveOrUpdateProductForm(productForm);
    return REDIRECT_PRODUCT_PAGE + savedProduct.getId();
  }
}