package com.azharjk.lms_backend.auth.template;

import com.azharjk.lms_backend.user.User;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseTemplate {
  private String message;
  private boolean verified;
  private User user;
}
