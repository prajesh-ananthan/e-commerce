package io.ecommerce.controller;

import com.google.common.annotations.VisibleForTesting;
import io.ecommerce.commands.CustomerForm;
import io.ecommerce.commands.validators.CustomerFormValidator;
import io.ecommerce.converters.CustomerToCustomerForm;
import io.ecommerce.domain.Customer;
import io.ecommerce.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Prajesh Ananthan
 *         Created on 23/7/2017.
 */
@Controller
@RequestMapping("/customer")
public class CustomerController {

  @VisibleForTesting
  static final String CUSTOMER = "customer";
  @VisibleForTesting
  static final String CUSTOMER_PAGE = CUSTOMER + "/" + CUSTOMER;
  @VisibleForTesting
  static final String CUSTOMERS = "customers";
  @VisibleForTesting
  static final String CUSTOMERS_PAGE = CUSTOMER + "/" + CUSTOMERS;
  @VisibleForTesting
  static final String REDIRECT_CUSTOMER_LIST = "redirect:/" + CUSTOMER + "/list/";
  @VisibleForTesting
  static final String REDIRECT_CUSTOMER_PAGE = "redirect:/" + CUSTOMER + "/";

  private static final String CUSTOMER_FORM_PAGE = CUSTOMER + "/customer-form";
  private static final String CUSTOMER_FORM = "customerForm";

  private CustomerService customerService;
  private CustomerToCustomerForm customerToCustomerForm;
  private CustomerFormValidator customerFormValidator;

  @Autowired
  public void setCustomerService(CustomerService customerService) {
    this.customerService = customerService;
  }

  @Autowired
  public void setCustomerToCustomerForm(CustomerToCustomerForm customerToCustomerForm) {
    this.customerToCustomerForm = customerToCustomerForm;
  }

  @Autowired
  public void setCustomerFormValidator(CustomerFormValidator customerFormValidator) {
    this.customerFormValidator = customerFormValidator;
  }

  @GetMapping(value = "/list")
  public String list(Model model) {
    List<Customer> customers = (List<Customer>) customerService.list();
    model.addAttribute(CUSTOMERS, customers);
    return CUSTOMERS_PAGE;
  }

  @GetMapping("/{id}")
  public String findById(@PathVariable Integer id, Model model) {
    Customer customer = customerService.findById(id);
    if (customer != null) {
      model.addAttribute(CUSTOMER, customer);
    }
    return CUSTOMER_PAGE;
  }

  @GetMapping("/new")
  public String create(Model model) {
    model.addAttribute(CUSTOMER_FORM, new CustomerForm());
    return CUSTOMER_FORM_PAGE;
  }

  @GetMapping("/edit/{id}")
  public String edit(@PathVariable Integer id, Model model) {
    final Customer customer = customerService.findById(id);
    model.addAttribute(CUSTOMER_FORM, customerToCustomerForm.convert(customer));
    return CUSTOMER_FORM_PAGE;
  }

  @GetMapping("/delete/{id}")
  public String delete(@PathVariable Integer id, Model model) {
    customerService.remove(id);
    return REDIRECT_CUSTOMER_LIST;
  }

  @PostMapping
  public String saveOrUpdate(@Valid CustomerForm customerForm, BindingResult bindingResult) {

    customerFormValidator.validate(customerForm, bindingResult);

    if (bindingResult.hasErrors()) {
      return CUSTOMER_FORM_PAGE;
    }

    Customer savedCustomer = customerService.saveOrUpdateCustomerForm(customerForm);
    return REDIRECT_CUSTOMER_PAGE + savedCustomer.getId();
  }
}
