package com.azharjk.lms_backend.auth;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.Optional;

import com.azharjk.lms_backend.user.UserRepository;
import com.azharjk.lms_backend.auth.template.LoginTemplate;
import com.azharjk.lms_backend.user.User;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class AuthServiceTest {
  @Autowired
  private AuthService authService;
  @MockBean
  private UserRepository userRepository;

  @Test
  public void authorize_shouldReturnNotNull_ifEmailExist() {
    User user = new User(1L, "a", "a@a", "a");
    when(userRepository.findByEmail("a@a")).thenReturn(Optional.of(user));

    assertNotNull(authService.authorize(new LoginTemplate("a@a", "a")));
  }

  @Test
  public void authorize_shouldReturnNull_ifEmailNotExist() {
    when(userRepository.findByEmail(anyString())).thenReturn(Optional.empty());

    assertNull(authService.authorize(new LoginTemplate("a@a", "a")));
  }

  @Test
  public void authorize_shouldReturnNotNull_ifPasswordAndEmailMatches() {
    User user = new User(1L, "a", "a@a", "a");
    when(userRepository.findByEmail("a@a")).thenReturn(Optional.of(user));

    assertNotNull(authService.authorize(new LoginTemplate("a@a", "a")));
  }

  @Test
  public void authorize_shouldReturnNull_ifPasswordNotMatches() {
    User user = new User(1L, "a", "a@a", "a");
    when(userRepository.findByEmail("a@a")).thenReturn(Optional.of(user));

    assertNull(authService.authorize(new LoginTemplate("a@a", "b")));
  }
}
