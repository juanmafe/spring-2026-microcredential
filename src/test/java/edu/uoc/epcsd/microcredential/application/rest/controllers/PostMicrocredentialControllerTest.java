package edu.uoc.epcsd.microcredential.application.rest.controllers;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.uoc.epcsd.microcredential.application.rest.config.MicrocredentialObjectMapper;
import edu.uoc.epcsd.microcredential.application.rest.generated.model.CreateMicrocredentialRequestDto;
import edu.uoc.epcsd.microcredential.application.rest.generated.model.MicrocredentialDto;
import edu.uoc.epcsd.microcredential.application.rest.mappers.MicrocredentialMapper;
import edu.uoc.epcsd.microcredential.domain.Microcredential;
import edu.uoc.epcsd.microcredential.domain.ports.in.MicrocredentialPort;
import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/** The type Post microcredential controller test. */
@ExtendWith(MockitoExtension.class)
class PostMicrocredentialControllerTest {

  /** The Url. */
  static final String URL = "/microcredentials";

  /** The Mock mvc. */
  MockMvc mockMvc;

  /** The Object mapper. */
  ObjectMapper objectMapper = MicrocredentialObjectMapper.get();

  /** The Post microcredential controller. */
  @InjectMocks PostMicrocredentialController postMicrocredentialController;

  /** The Microcredential port. */
  @Mock MicrocredentialPort microcredentialPort;

  /** The Microcredential mapper. */
  @Mock MicrocredentialMapper microcredentialMapper;

  /** Sets up. */
  @BeforeEach
  void setUp() {
    this.mockMvc = MockMvcBuilders.standaloneSetup(this.postMicrocredentialController).build();
  }

  /**
   * Test post microcredential when success then return created.
   *
   * @throws Exception the exception
   */
  @Test
  void testPostMicrocredential_whenSuccess_thenReturnCreated() throws Exception {

    // Given
    final var requestDto = Instancio.create(CreateMicrocredentialRequestDto.class);
    final var microcredential = Instancio.create(Microcredential.class);
    final var createdMicrocredential = Instancio.create(Microcredential.class);
    final var microcredentialDto = Instancio.create(MicrocredentialDto.class);

    // When
    when(this.microcredentialMapper.toDomain(requestDto)).thenReturn(microcredential);
    when(this.microcredentialPort.requestMicrocredential(microcredential))
        .thenReturn(createdMicrocredential);
    when(this.microcredentialMapper.toApi(createdMicrocredential)).thenReturn(microcredentialDto);

    final var response =
        this.mockMvc
            .perform(
                post(URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(this.objectMapper.writeValueAsString(requestDto)))
            .andExpect(status().isCreated());

    // Then
    verify(this.microcredentialMapper, times(1)).toDomain(requestDto);
    verify(this.microcredentialPort, times(1)).requestMicrocredential(microcredential);
    verify(this.microcredentialMapper, times(1)).toApi(createdMicrocredential);
    assertNotNull(response);
  }
}
