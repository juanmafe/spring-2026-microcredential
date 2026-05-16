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
import java.util.Collections;
import java.util.List;
import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/** The type Get microcredentials pending controller test. */
@ExtendWith(MockitoExtension.class)
class GetMicrocredentialsPendingControllerTest {

  /** The Url. */
  static final String URL = "/microcredentials/pending";

  /** The Mock mvc. */
  MockMvc mockMvc;

  /** The Get microcredentials pending controller. */
  @InjectMocks GetMicrocredentialsPendingController getMicrocredentialsPendingController;

  /** The Microcredential port. */
  @Mock MicrocredentialPort microcredentialPort;

  /** The Microcredential mapper. */
  @Mock MicrocredentialMapper microcredentialMapper;

  /** Sets up. */
  @BeforeEach
  void setUp() {
    this.mockMvc =
        MockMvcBuilders.standaloneSetup(this.getMicrocredentialsPendingController).build();
  }

  /**
   * Test get microcredentials pending when no pending microcredentials then return no content.
   *
   * @throws Exception the exception
   */
  @Test
  void testGetMicrocredentialsPending_whenNoPendingMicrocredentials_thenReturnNoContent()
      throws Exception {

    // When
    when(this.microcredentialPort.getPendingMicrocredentials()).thenReturn(Collections.emptyList());

    this.mockMvc.perform(get(URL)).andExpect(status().isNoContent());

    // Then
    verify(this.microcredentialPort, times(1)).getPendingMicrocredentials();
    verifyNoInteractions(this.microcredentialMapper);
  }

  /**
   * Test get microcredentials pending when pending microcredentials exist then return ok.
   *
   * @throws Exception the exception
   */
  @Test
  void testGetMicrocredentialsPending_whenPendingMicrocredentialsExist_thenReturnOk()
      throws Exception {

    // Given
    final List<Microcredential> pendingMicrocredentials =
        Instancio.ofList(Microcredential.class).size(2).create();
    final var microcredentialsDto = Instancio.ofList(MicrocredentialDto.class).size(2).create();

    // When
    when(this.microcredentialPort.getPendingMicrocredentials()).thenReturn(pendingMicrocredentials);
    when(this.microcredentialMapper.toApi(pendingMicrocredentials))
        .thenReturn(microcredentialsDto);

    final var response = this.mockMvc.perform(get(URL)).andExpect(status().isOk());

    // Then
    verify(this.microcredentialPort, times(1)).getPendingMicrocredentials();
    verify(this.microcredentialMapper, times(1)).toApi(pendingMicrocredentials);
    assertNotNull(response);
  }
}

