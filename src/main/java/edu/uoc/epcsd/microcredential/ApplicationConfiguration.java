package edu.uoc.epcsd.microcredential;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

/** The type Application configuration. */
@Configuration
public class ApplicationConfiguration {

  /**
   * Web client web client.
   *
   * @return the web client
   */
  @Bean
  public WebClient webClient() {
    return WebClient.builder().build();
  }
}
