package com.azharjk.lms_backend.user;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class UserService {
  private UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public List<User> getAllUsers() {
    return userRepository.findAll();
  }
}
