package com.azharjk.lms_backend.user;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
  private UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping
  public List<User> getAllUsers() {
    return userService.getAllUsers();
  }

  @GetMapping("/{userId}")
  public User getUserById(@PathVariable Long userId) {
    return userService.getUserById(userId);
  }

  @PostMapping
  public ResponseEntity<User> createUser(@RequestBody User user) {
    return new ResponseEntity<>(userService.createUser(user), HttpStatus.CREATED);
  }

  @PutMapping("/{userId}")
  public User editUser(@RequestBody User user, @PathVariable Long userId) {
    return userService.editUser(user, userId);
  }

  @DeleteMapping("/{userId}")
  @ResponseStatus(HttpStatus.ACCEPTED)
  public void deleteUser(@PathVariable Long userId) {
    userService.deleteUser(userId);
  }
}
