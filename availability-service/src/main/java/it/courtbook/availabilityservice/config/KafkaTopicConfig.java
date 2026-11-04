package it.courtbook.availabilityservice.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic prenotazioneEsito() {
        return new NewTopic(KafkaTopics.TOPIC_PRENOTAZIONE_ESITO, 3, (short) 1);
    }
}
