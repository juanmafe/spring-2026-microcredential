package edu.uoc.epcsd.microcredential.application.rest.controllers;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/** The type Patch microcredential approve controller test. */
@ExtendWith(MockitoExtension.class)
class PatchMicrocredentialApproveControllerTest {

  /** The Url. */
  static final String URL = "/microcredentials/{microcredentialId}/approve";

  /** The Mock mvc. */
  MockMvc mockMvc;

  /** The Patch microcredential approve controller. */
  @InjectMocks PatchMicrocredentialApproveController patchMicrocredentialApproveController;

  /** The Microcredential port. */
  @Mock MicrocredentialPort microcredentialPort;

  /** The Microcredential mapper. */
  @Mock MicrocredentialMapper microcredentialMapper;

  /** Sets up. */
  @BeforeEach
  void setUp() {
    this.mockMvc =
        MockMvcBuilders.standaloneSetup(this.patchMicrocredentialApproveController).build();
  }

  /**
   * Test patch microcredential approve when success then return ok.
   *
   * @throws Exception the exception
   */
  @Test
  void testPatchMicrocredentialApprove_whenSuccess_thenReturnOk() throws Exception {

    // Given
    final var microcredentialId = Instancio.create(Long.class);
    final var approvedMicrocredential = Instancio.create(Microcredential.class);
    final var microcredentialDto = Instancio.create(MicrocredentialDto.class);

    // When
    when(this.microcredentialPort.approvePendingMicrocredential(microcredentialId))
        .thenReturn(approvedMicrocredential);
    when(this.microcredentialMapper.toApi(approvedMicrocredential)).thenReturn(microcredentialDto);

    final var response =
        this.mockMvc.perform(patch(URL, microcredentialId)).andExpect(status().isOk());

    // Then
    verify(this.microcredentialPort, times(1)).approvePendingMicrocredential(microcredentialId);
    verify(this.microcredentialMapper, times(1)).toApi(approvedMicrocredential);
    assertNotNull(response);
  }
}

