package com.azharjk.lms_backend.user;

import com.github.javafaker.Faker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserSeeder {
  @Autowired
  private UserRepository userRepository;

  public void seed(int size) {
    Faker faker = new Faker();
    for (int i = 0; i < size; i++) {
      User user = new User();
      user.setEmail(faker.bothify("????##@g.com"));
      user.setFullname(faker.name().fullName());
      user.setPassword(faker.name().lastName() + faker.name().firstName());
      userRepository.save(user);
    }
  }
}
