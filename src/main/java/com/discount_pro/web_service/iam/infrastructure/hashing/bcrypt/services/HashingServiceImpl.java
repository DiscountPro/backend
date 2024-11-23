package com.discount_pro.web_service.iam.infrastructure.hashing.bcrypt.services;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.discount_pro.web_service.iam.infrastructure.hashing.bcrypt.BCryptHashingService;

/**
 * This class implements the {@link BCryptHashingService} interface.
 * It is used to hash passwords using the BCrypt algorithm.
 */
@Service
public class HashingServiceImpl implements BCryptHashingService {
  private final BCryptPasswordEncoder passwordEncoder;

  HashingServiceImpl() {
    this.passwordEncoder = new BCryptPasswordEncoder();
  }

  /**
   * Hash a password using the BCrypt algorithm
   * @param rawPassword the password to hash
   * @return String the hashed password
   */
  @Override
  public String encode(CharSequence rawPassword) {
    return passwordEncoder.encode(rawPassword);
  }

  /**
   * Check if a raw password matches a hashed password
   * @param rawPassword the raw password
   * @param encodedPassword the hashed password
   * @return boolean true if the raw password matches the hashed password, false otherwise
   */
  @Override
  public boolean matches(CharSequence rawPassword, String encodedPassword) {
    return passwordEncoder.matches(rawPassword, encodedPassword);
  }
}