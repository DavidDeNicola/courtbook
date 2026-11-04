package it.courtbook.availabilityservice.event;

import it.courtbook.availabilityservice.config.KafkaTopics;
import it.courtbook.availabilityservice.service.SlotService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class PrenotazioneListener {

    private final SlotService slotService;

    @KafkaListener(topics = KafkaTopics.TOPIC_PRENOTAZIONE_CREATA)
    public void onPrenotazioneCreata(PrenotazioneEvento evento) {
        log.info("Ricevuta richiesta per la prenotazione: {}", evento.idPrenotazione());
        slotService.gestisciRichiesta(evento);
    }
}
