package com.azharjk.lms_backend.user;

import com.github.javafaker.Faker;

import org.springframework.stereotype.Component;

@Component
public class UserSeeder {
  private UserRepository userRepository;
  private Faker faker;

  public UserSeeder(UserRepository userRepository) {
    this.userRepository = userRepository;
    this.faker = new Faker();
  }

  public void seed(int size) {
    for (int i = 0; i < size; i++) {
      User user = new User();
      user.setEmail(faker.bothify("????##@g.com"));
      user.setFullname(faker.name().fullName());
      user.setPassword(faker.name().lastName() + faker.name().firstName());
      userRepository.save(user);
    }
  }
}
