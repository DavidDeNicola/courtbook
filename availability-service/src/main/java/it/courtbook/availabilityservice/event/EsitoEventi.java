package it.courtbook.availabilityservice.event;

import it.courtbook.availabilityservice.config.KafkaTopics;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EsitoEventi {

    private final KafkaTemplate<String, EsitoEvento> kafkaTemplate;

    public void inviaEsito(EsitoEvento e) {
        kafkaTemplate.send(KafkaTopics.TOPIC_PRENOTAZIONE_ESITO, e.idCampo().toString(), e);
    }
}
