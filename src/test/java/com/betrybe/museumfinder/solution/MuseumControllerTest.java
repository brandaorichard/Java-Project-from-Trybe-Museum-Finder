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
@DisplayName("Controller Layer tests")
public class MuseumControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  @DisplayName("Should return a museum with valid id")
  void shouldReturnMuseumWithId() throws Exception {
    mockMvc.perform(get("/museums/299"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.name")
            .value("Museu do Trem"));
  }

  @Test
  @DisplayName("Should return status 404 with an invalid id")
  void shouldReturnStatusNotFound() throws Exception {
    mockMvc.perform(get("/museums/9999999"))
        .andExpect(status().isNotFound());
  }

  @Test
  @DisplayName("Should return the closest museum with some coordinates")
  void shouldReturnAMuseumByClosest() throws Exception {
    mockMvc.perform(get("/museums/closest?lat=0&lng=0&max_dist_km=100"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.name")
            .value("Museu da Imagem e do Som (Macapá)"));
  }
}
