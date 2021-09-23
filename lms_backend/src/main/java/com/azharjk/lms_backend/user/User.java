package com.azharjk.lms_backend.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "users")
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class User {
  @Id
  @GeneratedValue
  private Long id;

  @Column(nullable = false)
  private String fullname;

  @Column(nullable = false)
  private String email;

  @Column(nullable = false)
  private String password;

  public User(String fullname, String email, String password) {
    this.fullname = fullname;
    this.email = email;
    this.password = password;
  }
}
