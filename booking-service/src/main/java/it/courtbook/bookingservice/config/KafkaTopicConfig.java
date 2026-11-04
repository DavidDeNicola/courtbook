package it.courtbook.bookingservice.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic prenotazioneCreata() {
        return TopicBuilder.name(KafkaTopics.TOPIC_PRENOTAZIONE_CREATA)
                .partitions(3)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic prenotazioneCancellata() {
        return TopicBuilder.name(KafkaTopics.TOPIC_PRENOTAZIONE_CANCELLATA)
                .partitions(3)
                .replicas(1)
                .build();
    }
}
