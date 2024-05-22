package com.betrybe.museumfinder.solution;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("CollectionTypeController tests")
public class CollectionTypeControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  @DisplayName("Should return status 200")
  void shouldReturnStatusOk() throws Exception {
    mockMvc.perform(get("/collections/count/antropologia"))
        .andExpect(status().isOk());
  }

  @Test
  @DisplayName("Should return the body with correct values")
  void shouldReturnStatusOkAndCorrectBody() throws Exception {
    mockMvc.perform(get("/collections/count/antropologia"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.count").value(92));
  }

  @Test
  @DisplayName("Should return status 404")
  void shouldReturnStatusNotFound() throws Exception {
    mockMvc.perform(get("/collections/count/xablau"))
        .andExpect(status().isNotFound());
  }

}

