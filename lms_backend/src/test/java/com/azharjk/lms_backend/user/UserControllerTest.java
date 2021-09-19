package com.azharjk.lms_backend.user;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestPropertySource(locations = "classpath:application-test.properties")
public class UserControllerTest {
  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private UserSeeder userSeeder;

  @BeforeAll
  public void setUp() {
    userSeeder.seed(2);
  }

  public String objectToJson(User user) throws Exception {
    ObjectMapper objectMapper = new ObjectMapper();
    String json = objectMapper.writeValueAsString(user);

    return json;
  }

  @Test
  public void getAllUsers_shouldReturnListOfUsers_shouldHaveStatusCode200() throws Exception {
    mockMvc.perform(get("/users"))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$", hasSize(2)));
  }

  @Test
  public void getUserById_shouldReturnUserObject_shouldHaveStatusCode200() throws Exception {
    mockMvc.perform(get("/users/1"))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.id", is(1)));
  }

  @Test
  public void createUser_shouldReturnUserObject_shouldHaveStatusCode201() throws Exception {
    User user = new User("Mark Rockwell", "mrkrock@g.com", "markgodson");

    mockMvc.perform(post("/users")
      .contentType(MediaType.APPLICATION_JSON)
      .characterEncoding("utf-8")
      .content(objectToJson(user)))
    .andExpect(status().isCreated())
    .andExpect(jsonPath("$.id", is(3)));
  }

  @Test
  public void editUser_shouldReturnUserObject_shouldHaveStatusCode200() throws Exception {
    User user = new User("Mark Rockwell", "mrkrock@g.com", "markgodson");

    mockMvc.perform(put("/users/1")
      .contentType(MediaType.APPLICATION_JSON)
      .characterEncoding("utf-8")
      .content(objectToJson(user)))
    .andExpect(status().isOk())
    .andExpect(jsonPath("$.id", is(1)));
  }

  @Test
  public void deleteUser_shouldHaveStatusCode202() throws Exception {
    mockMvc.perform(delete("/users/1"))
      .andExpect(status().isAccepted());
  }
}
