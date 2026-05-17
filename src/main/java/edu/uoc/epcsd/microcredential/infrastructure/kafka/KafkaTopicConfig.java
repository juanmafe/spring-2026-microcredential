package edu.uoc.epcsd.microcredential.infrastructure.kafka;

import java.util.HashMap;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;

/** The type Kafka topic config. */
@Configuration
public class KafkaTopicConfig {

  @Value(value = "${spring.kafka.bootstrap-servers}")
  private String bootstrapAddress;

  /**
   * Kafka admin.
   *
   * @return the Kafka admin
   */
  @Bean
  public KafkaAdmin kafkaAdmin() {
    final var configs = new HashMap<String, Object>();
    configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
    return new KafkaAdmin(configs);
  }

  /**
   * Pending microcredential topic new topic.
   *
   * @return the new topic
   */
  @Bean
  public NewTopic pendingMicrocredentialTopic() {
    return new NewTopic(
        KafkaConstants.MICROCREDENTIAL_TOPIC + KafkaConstants.SEPARATOR + KafkaConstants.PENDING,
        1,
        (short) 1);
  }

  /**
   * Approved microcredential topic new topic.
   *
   * @return the new topic
   */
  @Bean
  public NewTopic approvedMicrocredentialTopic() {
    return new NewTopic(
        KafkaConstants.MICROCREDENTIAL_TOPIC + KafkaConstants.SEPARATOR + KafkaConstants.APPROVED,
        1,
        (short) 1);
  }

  /**
   * Rejected microcredential topic new topic.
   *
   * @return the new topic
   */
  @Bean
  public NewTopic rejectedMicrocredentialTopic() {
    return new NewTopic(
        KafkaConstants.MICROCREDENTIAL_TOPIC + KafkaConstants.SEPARATOR + KafkaConstants.REJECTED,
        1,
        (short) 1);
  }
}
