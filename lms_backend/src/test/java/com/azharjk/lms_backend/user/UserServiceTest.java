package com.azharjk.lms_backend.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class UserServiceTest {
  @Autowired
  private UserService userService;
  @MockBean
  private UserRepository userRepository;

  @Test
  public void getAllUsers_shouldReturnListOfUserObject() {
    when(userRepository.findAll()).thenReturn(List.of(
      new User(1L, "Mark Rockwell", "markrck@g.com", "markhans12"),
      new User(2L, "Tike Misery", "tikmis@g.com", "tikmis1")
    ));

    assertEquals(2, userService.getAllUsers().size());
  }

  @Test
  public void getUserById_shouldReturnUserObject_ifIdIsPresented() {
    when(userRepository.findById(1L)).thenReturn(Optional.of(
      new User(1L, "Mark Rockwell", "markrck@g.com", "markhans12")
    ));

    assertEquals("Mark Rockwell", userService.getUserById(1L).getFullname());
  }

  @Test
  public void getUserById_shouldReturnNull_ifIdNotPresented() {
    when(userRepository.findById(2L)).thenReturn(Optional.empty());

    assertEquals(null, userService.getUserById(2L));
  }

  @Test
  public void createUser_shouldReturnUserObject() {
    User user = new User(1L, "Mark Rockwell", "markrck@g.com", "markhans12");
    when(userRepository.save(user)).thenReturn(user);

    assertEquals(user.getFullname(), userService.createUser(user).getFullname());
  }

  @Test
  public void editUser_shouldReturnUserObject() {
    User user = new User(1L, "Mark Rockwell", "markrck@g.com", "markhans12");
    User updatedUser = new User(1L, "John Manner", "jhnsmnnr@g.com", "ilikeujon");
    when(userRepository.save(user)).thenReturn(updatedUser);

    assertEquals(user.getId(), userService.editUser(user, 1L).getId());
  }

  @Test
  public void deleteUser_shouldBeCalled() {
    userService.deleteUser(anyLong());
    verify(userRepository).deleteById(anyLong());
  }
}
