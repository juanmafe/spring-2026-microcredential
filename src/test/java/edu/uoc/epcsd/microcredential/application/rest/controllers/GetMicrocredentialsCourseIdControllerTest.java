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

/** The type Get microcredentials course id controller test. */
@ExtendWith(MockitoExtension.class)
class GetMicrocredentialsCourseIdControllerTest {

  /** The Url. */
  static final String URL = "/microcredentials/course/{courseId}";

  /** The Mock mvc. */
  MockMvc mockMvc;

  /** The Get microcredentials course id controller. */
  @InjectMocks GetMicrocredentialsCourseIdController getMicrocredentialsCourseIdController;

  /** The Microcredential port. */
  @Mock MicrocredentialPort microcredentialPort;

  /** The Microcredential mapper. */
  @Mock MicrocredentialMapper microcredentialMapper;

  /** Sets up. */
  @BeforeEach
  void setUp() {
    this.mockMvc =
        MockMvcBuilders.standaloneSetup(this.getMicrocredentialsCourseIdController).build();
  }

  /**
   * Test get microcredentials course id when no microcredentials then return no content.
   *
   * @throws Exception the exception
   */
  @Test
  void testGetMicrocredentialsCourseId_whenNoMicrocredentials_thenReturnNoContent()
      throws Exception {

    // Given
    final var courseId = Instancio.create(Long.class);

    // When
    when(this.microcredentialPort.getCourseMicrocredentials(courseId))
        .thenReturn(Collections.emptyList());

    this.mockMvc.perform(get(URL, courseId)).andExpect(status().isNoContent());

    // Then
    verify(this.microcredentialPort, times(1)).getCourseMicrocredentials(courseId);
    verifyNoInteractions(this.microcredentialMapper);
  }

  /**
   * Test get microcredentials course id when microcredentials exist then return ok.
   *
   * @throws Exception the exception
   */
  @Test
  void testGetMicrocredentialsCourseId_whenMicrocredentialsExist_thenReturnOk() throws Exception {

    // Given
    final var courseId = Instancio.create(Long.class);
    final List<Microcredential> microcredentials =
        Instancio.ofList(Microcredential.class).size(2).create();
    final var microcredentialsDto = Instancio.ofList(MicrocredentialDto.class).size(2).create();

    // When
    when(this.microcredentialPort.getCourseMicrocredentials(courseId))
        .thenReturn(microcredentials);
    when(this.microcredentialMapper.toApi(microcredentials)).thenReturn(microcredentialsDto);

    final var response = this.mockMvc.perform(get(URL, courseId)).andExpect(status().isOk());

    // Then
    verify(this.microcredentialPort, times(1)).getCourseMicrocredentials(courseId);
    verify(this.microcredentialMapper, times(1)).toApi(microcredentials);
    assertNotNull(response);
  }
}

