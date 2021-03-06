package io.ecommerce.converters;

import io.ecommerce.commands.CustomerForm;
import io.ecommerce.domain.Address;
import io.ecommerce.domain.Customer;
import io.ecommerce.domain.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * @author Prajesh Ananthan
 *         Created on 18/8/2017.
 */
@Component
public class CustomerFormToCustomer implements Converter<CustomerForm, Customer> {

  @Override
  public Customer convert(CustomerForm customerForm) {
    Customer customer = new Customer();
    customer.setBillingAddress(new Address());
    customer.setUser(new User());
//    customer.setShippingAddress(new Address());
    customer.getUser().setId(customerForm.getUserId());
    customer.getUser().setVersion(customerForm.getUserVersion());
    customer.setId(customerForm.getCustomerId());
    customer.setVersion(customerForm.getCustomerVersion());
    customer.getUser().setUserName(customerForm.getUserName());
    customer.getUser().setPassword(customerForm.getPasswordText());
    customer.setFirstName(customerForm.getFirstName());
    customer.setLastName(customerForm.getLastName());
    customer.setEmail(customerForm.getEmail());
    customer.setPhoneNumber(customerForm.getPhoneNumber());
    return customer;
  }
}
