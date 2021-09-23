package com.azharjk.lms_backend.auth;

import static java.util.Objects.isNull;

import com.azharjk.lms_backend.user.User;
import com.azharjk.lms_backend.auth.template.LoginTemplate;
import com.azharjk.lms_backend.auth.template.ResponseTemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
  @Autowired
  private AuthService authService;

  @PostMapping("/verify-user-login")
  public ResponseEntity<ResponseTemplate> verifyUserLogin(@RequestBody LoginTemplate template) {
    User user = authService.authorize(template);
    if (isNull(user)) {
      return new ResponseEntity<>(new ResponseTemplate("Unsuccessful Authentication", false, null), HttpStatus.UNAUTHORIZED);
    }
    return new ResponseEntity<>(new ResponseTemplate("Successful Authentication", true, user), HttpStatus.OK);
  }
}
