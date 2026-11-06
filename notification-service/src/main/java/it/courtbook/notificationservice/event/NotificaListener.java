package it.courtbook.notificationservice.event;

import it.courtbook.notificationservice.config.KafkaTopics;
import it.courtbook.notificationservice.service.NotificaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class NotificaListener {

    private final NotificaService notificaService;

    @KafkaListener(topics = KafkaTopics.TOPIC_PRENOTAZIONE_ESITO, properties = "spring.json.value.default.type=it.courtbook.notificationservice.event.EsitoEvento")
    public void onEsito(EsitoEvento evento) {
        log.info("Ricevuto esito {} per la prenotazione {}", evento.esito(), evento.idPrenotazione());
        notificaService.registraEsito(evento);
    }

    @KafkaListener(topics = KafkaTopics.TOPIC_PRENOTAZIONE_CANCELLATA, properties = "spring.json.value.default.type=it.courtbook.notificationservice.event.PrenotazioneEvento")
    public void onCancellazione(PrenotazioneEvento evento) {
        log.info("Ricevuta cancellazione per la prenotazione {}", evento.idPrenotazione());
        notificaService.registraCancellazione(evento);
    }
}
