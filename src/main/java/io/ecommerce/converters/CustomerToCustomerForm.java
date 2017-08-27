package io.ecommerce.converters;

import io.ecommerce.commands.CustomerForm;
import io.ecommerce.domain.Customer;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * @author Prajesh Ananthan
 *         Created on 27/8/2017.
 */
@Component
public class CustomerToCustomerForm implements Converter<Customer, CustomerForm> {

  @Override
  public CustomerForm convert(Customer customer) {
    CustomerForm customerForm = new CustomerForm();
    customerForm.setCustomerId(customer.getId());
    customerForm.setCustomerVersion(customer.getVersion());
    customerForm.setEmail(customer.getEmail());
    customerForm.setFirstName(customer.getFirstName());
    customerForm.setLastName(customer.getLastName());
    return customerForm;
  }
}
