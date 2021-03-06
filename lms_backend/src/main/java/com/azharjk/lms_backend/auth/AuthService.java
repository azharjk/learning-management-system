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

  public User authorize(LoginTemplate template) {
    Optional<User> user = userRepository.findByEmail(template.getEmail());
    if (user.isPresent()) {
      User u = user.get();
      if (u.getPassword().equals(template.getPassword())) {
        return u;
      }
      return null;
    }
    return null;
  }
}
