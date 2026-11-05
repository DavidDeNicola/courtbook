package it.courtbook.bookingservice.service;

import it.courtbook.bookingservice.dto.PrenotazioneRequest;
import it.courtbook.bookingservice.dto.PrenotazioneResponse;
import it.courtbook.bookingservice.event.EsitoEvento;
import it.courtbook.bookingservice.event.PrenotazioneEventi;
import it.courtbook.bookingservice.exception.PrenotazioneNonCancellabileException;
import it.courtbook.bookingservice.exception.PrenotazioneNonCreabileException;
import it.courtbook.bookingservice.exception.PrenotazioneNonTrovataException;
import it.courtbook.bookingservice.mapper.PrenotazioneMapper;
import it.courtbook.bookingservice.model.Prenotazione;
import it.courtbook.bookingservice.model.StatoPrenotazione;
import it.courtbook.bookingservice.repository.PrenotazioneRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class PrenotazioneService {

    private final PrenotazioneRepository prenotazioneRepository;
    private final PrenotazioneMapper prenotazioneMapper;
    private final PrenotazioneEventi prenotazioneEventi;

    public PrenotazioneResponse crea(PrenotazioneRequest request) {
        if (!request.fine().isAfter(request.inizio())) { throw new PrenotazioneNonCreabileException("Data inizio deve essere precedente alla data fine"); }

        Prenotazione p = prenotazioneRepository.save(prenotazioneMapper.fromRequest(request));

        prenotazioneEventi.pubblicaCreata(prenotazioneMapper.toEvent(p));

        return prenotazioneMapper.toResponse(p);
    }

    public List<PrenotazioneResponse> trovaTutte() {
        return prenotazioneRepository.findAll().stream().map(prenotazioneMapper::toResponse).toList();
    }

    public PrenotazioneResponse trovaPerId(Long id) {
        return prenotazioneMapper.toResponse(prenotazioneRepository.findById(id).orElseThrow(() -> new PrenotazioneNonTrovataException("Prenotazione non trovata")));
    }

    public PrenotazioneResponse cancella(Long id) {

        Prenotazione prenotazione = prenotazioneRepository.findById(id).orElseThrow(() -> new PrenotazioneNonTrovataException("Prenotazione non trovata"));

        if (prenotazione.getStato() != StatoPrenotazione.CONFIRMED) { throw new PrenotazioneNonCancellabileException("Prenotazione non cancellabile"); }

        prenotazione.setStato(StatoPrenotazione.CANCELLED);

        Prenotazione salvata = prenotazioneRepository.save(prenotazione);

        prenotazioneEventi.pubblicaCancellata(prenotazioneMapper.toEvent(salvata));

        return prenotazioneMapper.toResponse(salvata);
    }

    @Transactional
    public void aggiornaStato(EsitoEvento evento) {
        Optional<Prenotazione> trovata = prenotazioneRepository.findById(evento.idPrenotazione());

        if (trovata.isEmpty()) {
            log.warn("Esito per prenotazione inesistente: {}", evento.idPrenotazione());
            return;
        }

        Prenotazione prenotazione = trovata.get();

        if(prenotazione.getStato() != StatoPrenotazione.PENDING) {
            return;
        }

        StatoPrenotazione nuovoStato = switch (evento.esito()) {
            case RIFIUTATA -> StatoPrenotazione.REJECTED;
            case CONFERMATA ->  StatoPrenotazione.CONFIRMED;
        };

        prenotazione.setStato(nuovoStato);

        prenotazioneRepository.save(prenotazione);

    }
}
