package it.courtbook.bookingservice.event;

import it.courtbook.bookingservice.config.KafkaTopics;
import it.courtbook.bookingservice.service.PrenotazioneService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class EsitoListener {

    private final PrenotazioneService prenotazioneService;

    @KafkaListener(topics = KafkaTopics.TOPIC_PRENOTAZIONE_ESITO)
    public void onEsito(EsitoEvento evento) {
        log.info("Ricevuto esito {} per la prenotazione {}", evento.esito(), evento.idPrenotazione());
        prenotazioneService.aggiornaStato(evento);
    }

}
