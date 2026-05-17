package edu.uoc.epcsd.microcredential.infrastructure.kafka;

import java.util.HashMap;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

/** The type Kafka producer config. */
@Configuration
public class KafkaProducerConfig {

  @Value(value = "${spring.kafka.bootstrap-servers}")
  private String bootstrapAddress;

  /**
   * Microcredential message producer factory.
   *
   * @return the producer factory
   */
  @Bean
  public ProducerFactory<String, MicrocredentialMessage> microcredentialMessageProducerFactory() {
    final var configProps = new HashMap<String, Object>();
    configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
    configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
    configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
    return new DefaultKafkaProducerFactory<>(configProps);
  }

  /**
   * Microcredential kafka template.
   *
   * @return the Kafka template
   */
  @Bean
  public KafkaTemplate<String, MicrocredentialMessage> microcredentialKafkaTemplate() {
    return new KafkaTemplate<>(microcredentialMessageProducerFactory());
  }
}
