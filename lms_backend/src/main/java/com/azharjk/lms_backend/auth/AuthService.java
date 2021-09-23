package com.azharjk.lms_backend.auth;

import java.util.Optional;

import com.azharjk.lms_backend.auth.template.LoginTemplate;
import com.azharjk.lms_backend.user.User;
import com.azharjk.lms_backend.user.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
  @Autowired
  private UserRepository userRepository;

  public boolean isAuthorize(LoginTemplate template) {
    Optional<User> user = userRepository.findByEmail(template.getEmail());
    if (!user.isPresent()) {
      return false;
    }
    return user.get().getPassword().equals(template.getPassword());
  }
}
