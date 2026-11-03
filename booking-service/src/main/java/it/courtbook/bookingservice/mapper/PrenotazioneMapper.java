package it.courtbook.bookingservice.mapper;

import it.courtbook.bookingservice.dto.PrenotazioneRequest;
import it.courtbook.bookingservice.dto.PrenotazioneResponse;
import it.courtbook.bookingservice.event.PrenotazioneEvento;
import it.courtbook.bookingservice.model.Prenotazione;
import org.springframework.stereotype.Component;

@Component
public class PrenotazioneMapper {

    public PrenotazioneResponse toResponse(Prenotazione p){
        return new PrenotazioneResponse(
                p.getId(),
                p.getIdUtente(),
                p.getIdCampo(),
                p.getInizio(),
                p.getFine(),
                p.getStato()
        );
    }

    public Prenotazione fromRequest(PrenotazioneRequest p){
        return new Prenotazione(
                p.idUtente(),
                p.idCampo(),
                p.inizio(),
                p.fine()
        );
    }

    public PrenotazioneEvento toEvent(Prenotazione p){
        return  new PrenotazioneEvento(
                p.getId(),
                p.getIdUtente(),
                p.getIdCampo(),
                p.getInizio(),
                p.getFine()
        );
    }
}
