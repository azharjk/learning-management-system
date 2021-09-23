package com.azharjk.lms_backend.auth;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
  public void isAuthorize_shouldReturnTrue_ifEmailExist() {
    User user = new User(1L, "a", "a@a", "a");
    when(userRepository.findByEmail("a@a")).thenReturn(Optional.of(user));

    assertTrue(authService.isAuthorize(new LoginTemplate("a@a", "a")));
  }

  @Test
  public void isAuthorize_shouldReturnFalse_ifEmailNotExist() {
    when(userRepository.findByEmail(anyString())).thenReturn(Optional.empty());

    assertFalse(authService.isAuthorize(new LoginTemplate("a@a", "a")));
  }

  @Test
  public void isAuthorize_shouldReturnTrue_ifPasswordAndEmailMatches() {
    User user = new User(1L, "a", "a@a", "a");
    when(userRepository.findByEmail("a@a")).thenReturn(Optional.of(user));

    assertTrue(authService.isAuthorize(new LoginTemplate("a@a", "a")));
  }

  @Test
  public void isAuthorize_shouldReturnFalse_ifPasswordNotMatches() {
    User user = new User(1L, "a", "a@a", "a");
    when(userRepository.findByEmail("a@a")).thenReturn(Optional.of(user));

    assertFalse(authService.isAuthorize(new LoginTemplate("a@a", "b")));
  }
}
