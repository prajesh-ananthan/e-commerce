package io.ecommerce.commands.validators;

import io.ecommerce.commands.CustomerForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.ResourceBundle;

/**
 * @author Prajesh Ananthan
 *         Created on 27/8/2017.
 */
@Component
public class CustomerFormValidator implements Validator {

  private ResourceBundle resourceBundle;

  @Autowired
  public void setResourceBundle(ResourceBundle resourceBundle) {
    this.resourceBundle = resourceBundle;
  }

  @Override
  public boolean supports(Class<?> clazz) {
    return CustomerForm.class.equals(clazz);
  }

  @Override
  public void validate(Object target, Errors errors) {
    CustomerForm customerForm = (CustomerForm) target;

    if (!customerForm.getPasswordText().equals(customerForm.getPasswordTextConfirm())) {
      errors.rejectValue("passwordText", "PasswordsDontMatch.customerForm.passwordText", resourceBundle.getString("PasswordsDontMatch"));
      errors.rejectValue("passwordTextConfirm", "PasswordsDontMatch.customerForm.passwordTextConfirm", resourceBundle.getString("PasswordsDontMatch"));
    }
  }
}
