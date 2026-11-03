package it.courtbook.bookingservice.service;

import it.courtbook.bookingservice.dto.PrenotazioneRequest;
import it.courtbook.bookingservice.dto.PrenotazioneResponse;
import it.courtbook.bookingservice.event.PrenotazioneEventi;
import it.courtbook.bookingservice.exception.PrenotazioneNonCancellabileException;
import it.courtbook.bookingservice.exception.PrenotazioneNonCreabileException;
import it.courtbook.bookingservice.exception.PrenotazioneNonTrovataException;
import it.courtbook.bookingservice.mapper.PrenotazioneMapper;
import it.courtbook.bookingservice.model.Prenotazione;
import it.courtbook.bookingservice.model.StatoPrenotazione;
import it.courtbook.bookingservice.repository.PrenotazioneRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
