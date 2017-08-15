package io.prajesh.service.security;

import org.jasypt.util.password.StrongPasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Prajesh Ananthan
 *         Created on 30/7/2017.
 */
@Service
public class EncryptPasswordServiceImpl implements EncryptionService {

  private StrongPasswordEncryptor strongEncryptor;

  @Autowired
  public EncryptPasswordServiceImpl(StrongPasswordEncryptor strongEncryptor) {
    this.strongEncryptor = strongEncryptor;
  }

  @Override
  public String encryptString(String input) {
    return strongEncryptor.encryptPassword(input);
  }

  @Override
  public boolean checkPassword(String plainPassword, String encryyptPassword) {
    return strongEncryptor.checkPassword(plainPassword, encryyptPassword);
  }
}
