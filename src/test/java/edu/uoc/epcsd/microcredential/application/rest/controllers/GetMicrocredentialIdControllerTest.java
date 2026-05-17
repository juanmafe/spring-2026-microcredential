package edu.uoc.epcsd.microcredential.application.rest.controllers;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import edu.uoc.epcsd.microcredential.application.rest.generated.model.MicrocredentialDto;
import edu.uoc.epcsd.microcredential.application.rest.mappers.MicrocredentialMapper;
import edu.uoc.epcsd.microcredential.domain.Microcredential;
import edu.uoc.epcsd.microcredential.domain.ports.in.MicrocredentialPort;
import java.util.Optional;
import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/** The type Get microcredential id controller test. */
@ExtendWith(MockitoExtension.class)
class GetMicrocredentialIdControllerTest {

  /** The Url. */
  static final String URL = "/microcredentials/{microcredentialId}";

  /** The Mock mvc. */
  MockMvc mockMvc;

  /** The Get microcredential id controller. */
  @InjectMocks GetMicrocredentialIdController getMicrocredentialIdController;

  /** The Microcredential port. */
  @Mock MicrocredentialPort microcredentialPort;

  /** The Microcredential mapper. */
  @Mock MicrocredentialMapper microcredentialMapper;

  /** Sets up. */
  @BeforeEach
  void setUp() {
    this.mockMvc = MockMvcBuilders.standaloneSetup(this.getMicrocredentialIdController).build();
  }

  /**
   * Test get microcredential by id when not found then return no content.
   *
   * @throws Exception the exception
   */
  @Test
  void testGetMicrocredentialById_whenNotFound_thenReturnNoContent() throws Exception {

    // Given
    final var microcredentialId = Instancio.create(Long.class);

    // When
    when(this.microcredentialPort.getById(microcredentialId)).thenReturn(Optional.empty());

    this.mockMvc.perform(get(URL, microcredentialId)).andExpect(status().isNoContent());

    // Then
    verify(this.microcredentialPort, times(1)).getById(microcredentialId);
    verifyNoInteractions(this.microcredentialMapper);
  }

  /**
   * Test get microcredential by id when found then return ok.
   *
   * @throws Exception the exception
   */
  @Test
  void testGetMicrocredentialById_whenFound_thenReturnOk() throws Exception {

    // Given
    final var microcredential = Instancio.create(Microcredential.class);
    final var microcredentialDto = Instancio.create(MicrocredentialDto.class);

    // When
    when(this.microcredentialPort.getById(microcredential.id()))
        .thenReturn(Optional.of(microcredential));
    when(this.microcredentialMapper.toApi(microcredential)).thenReturn(microcredentialDto);

    final var response =
        this.mockMvc.perform(get(URL, microcredential.id())).andExpect(status().isOk());

    // Then
    verify(this.microcredentialPort, times(1)).getById(microcredential.id());
    verify(this.microcredentialMapper, times(1)).toApi(microcredential);
    assertNotNull(response);
  }
}
