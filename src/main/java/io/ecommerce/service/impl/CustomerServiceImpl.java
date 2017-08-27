package io.ecommerce.service.impl;

import io.ecommerce.commands.CustomerForm;
import io.ecommerce.converters.CustomerFormToCustomer;
import io.ecommerce.domain.Customer;
import io.ecommerce.repositories.CustomerRepository;
import io.ecommerce.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Prajesh Ananthan
 *         Created on 17/8/2017.
 */
@Service
public class CustomerServiceImpl implements CustomerService {

  private CustomerRepository customerRepository;
  private CustomerFormToCustomer customerFormToCustomer;

  @Autowired
  public void setCustomerRepository(CustomerRepository customerRepository) {
    this.customerRepository = customerRepository;
  }

  @Autowired
  public void setCustomerFormToCustomer(CustomerFormToCustomer customerFormToCustomer) {
    this.customerFormToCustomer = customerFormToCustomer;
  }

  @Override
  public List<?> list() {
    List<Customer> customers = new ArrayList<>();
    customerRepository.findAll().forEach(customers::add);
    return customers;
  }

  @Override
  public Customer findById(Integer id) {
    return customerRepository.findOne(id);
  }

  @Override
  public Customer saveOrUpdate(Customer domainObject) {
    return customerRepository.save(domainObject);
  }

  @Override
  public void remove(Integer id) {
    customerRepository.delete(id);
  }

  @Override
  public Customer saveOrUpdateCustomerForm(CustomerForm customerForm) {
    Customer newCustomer = customerFormToCustomer.convert(customerForm);
    if (newCustomer.getUser().getId() != null) {
      Customer existingCustomer = findById(newCustomer.getId());
      newCustomer.getUser().setEnabled(existingCustomer.getUser().getEnabled());
    }
    return customerRepository.save(newCustomer);
  }
}
