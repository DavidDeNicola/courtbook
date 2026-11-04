package it.courtbook.bookingservice.event;

import it.courtbook.bookingservice.config.KafkaTopics;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PrenotazioneEventi {

    private final KafkaTemplate<String, PrenotazioneEvento> kafkaTemplate;

    public void pubblicaCreata(PrenotazioneEvento p) {
        kafkaTemplate.send( KafkaTopics.TOPIC_PRENOTAZIONE_CREATA , p.idCampo().toString(), p);
    }

    public void pubblicaCancellata(PrenotazioneEvento p) {
        kafkaTemplate.send( KafkaTopics.TOPIC_PRENOTAZIONE_CANCELLATA , p.idCampo().toString(), p);
    }
}
