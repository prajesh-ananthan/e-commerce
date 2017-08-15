package io.prajesh.service.security;

import org.springframework.stereotype.Service;

/**
 * @author Prajesh Ananthan
 *         Created on 30/7/2017.
 */
@Service
public interface EncryptionService {
  String encryptString(String input);

  boolean checkPassword(String plainPassword, String encryyptPassword);
}
