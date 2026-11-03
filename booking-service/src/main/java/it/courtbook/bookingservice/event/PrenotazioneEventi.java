package it.courtbook.bookingservice.event;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PrenotazioneEventi {

    private final KafkaTemplate<String, PrenotazioneEvento> kafkaTemplate;
    private static final String TOPIC_PRENOTAZIONE_CREATA = "prenotazione-creata";
    private static final String TOPIC_PRENOTAZIONE_CANCELLATA = "prenotazione-cancellata";

    public void pubblicaCreata(PrenotazioneEvento p) {
        kafkaTemplate.send( TOPIC_PRENOTAZIONE_CREATA , p.idCampo().toString(), p);
    }

    public void pubblicaCancellata(PrenotazioneEvento p) {
        kafkaTemplate.send( TOPIC_PRENOTAZIONE_CANCELLATA , p.idCampo().toString(), p);
    }
}
