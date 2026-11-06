package it.courtbook.notificationservice.service;

import it.courtbook.notificationservice.event.EsitoEvento;
import it.courtbook.notificationservice.event.PrenotazioneEvento;
import it.courtbook.notificationservice.mapper.NotificaMapper;
import it.courtbook.notificationservice.model.Notifica;
import it.courtbook.notificationservice.repository.NotificaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificaService {

    private final NotificaRepository notificaRepository;
    private final NotificaMapper notificaMapper;

    public void registraEsito(EsitoEvento evento) {
        salvaSeNuova(notificaMapper.fromEsito(evento));
    }

    public void registraCancellazione(PrenotazioneEvento evento) {
        salvaSeNuova(notificaMapper.fromCancellazione(evento));
    }

    private void salvaSeNuova(Notifica notifica) {
        if (notificaRepository.existsByIdPrenotazioneAndTipo(notifica.getIdPrenotazione(), notifica.getTipo())) {
            log.info("Notifica {} già presente per la prenotazione {}", notifica.getTipo(), notifica.getIdPrenotazione());
            return;
        }
        notificaRepository.save(notifica);
        log.info("Salvata notifica {} per l'utente {}", notifica.getTipo(), notifica.getIdUtente());
    }
}
