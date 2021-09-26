package com.azharjk.lms_backend.auth;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.IsNull.nullValue;

import com.azharjk.lms_backend.auth.template.LoginTemplate;
import com.azharjk.lms_backend.user.UserSeeder;
import com.azharjk.lms_backend.user.User;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestPropertySource(locations = "classpath:application-test.properties")
@DirtiesContext
public class AuthControllerTest {
  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private UserSeeder userSeeder;

  private User actualUser;

  @BeforeAll
  public void setUp() {
    actualUser = new User(1L, "a", "a@a", "a");
    userSeeder.seedSpecific(actualUser);
  }

  private String objectToJson(LoginTemplate template) throws Exception {
    ObjectMapper objectMapper = new ObjectMapper();
    String json = objectMapper.writeValueAsString(template);

    return json;
  }

  @Test
  public void verifyUserLogin_shouldHaveStatusCode200_ifCredentialsMatches() throws Exception {
    LoginTemplate user = new LoginTemplate("a@a", "a");

    mockMvc.perform(post("/auth/verify-user-login")
      .contentType(MediaType.APPLICATION_JSON)
      .characterEncoding("utf-8")
      .content(objectToJson(user)))
    .andExpect(status().isOk())
    .andExpect(jsonPath("$.message", is("Successful Authentication")))
    .andExpect(jsonPath("$.verified", is(true)))
    .andExpect(jsonPath("$.user.*", hasSize(2)))
    .andExpect(jsonPath("$.user.email", is(actualUser.getEmail())))
    .andExpect(jsonPath("$.user.fullname", is(actualUser.getFullname())));
  }

  @Test
  public void verifyUserLogin_shouldHaveStatusCode401_ifPasswordNotMatches() throws Exception {
    LoginTemplate user = new LoginTemplate("a@a", "b");

    mockMvc.perform(post("/auth/verify-user-login")
      .contentType(MediaType.APPLICATION_JSON)
      .characterEncoding("utf-8")
      .content(objectToJson(user)))
    .andExpect(status().isUnauthorized())
    .andExpect(jsonPath("$.message", is("Unsuccessful Authentication")))
    .andExpect(jsonPath("$.verified", is(false)))
    .andExpect(jsonPath("$.user", nullValue()));
  }

  @Test
  public void verifyUserLogin_shouldHaveStatusCode401_ifEmailNotMatches() throws Exception {
    LoginTemplate user = new LoginTemplate("b@a", "a");

    mockMvc.perform(post("/auth/verify-user-login")
      .contentType(MediaType.APPLICATION_JSON)
      .characterEncoding("utf-8")
      .content(objectToJson(user)))
    .andExpect(status().isUnauthorized())
    .andExpect(jsonPath("$.message", is("Unsuccessful Authentication")))
    .andExpect(jsonPath("$.verified", is(false)))
    .andExpect(jsonPath("$.user", nullValue()));
  }
}
