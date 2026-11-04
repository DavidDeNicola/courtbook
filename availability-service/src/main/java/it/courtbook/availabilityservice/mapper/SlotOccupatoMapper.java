package it.courtbook.availabilityservice.mapper;

import it.courtbook.availabilityservice.dto.SlotResponse;
import it.courtbook.availabilityservice.event.PrenotazioneEvento;
import it.courtbook.availabilityservice.model.Campo;
import it.courtbook.availabilityservice.model.SlotOccupato;
import org.springframework.stereotype.Component;

@Component
public class SlotOccupatoMapper {

    public SlotResponse toResponse(SlotOccupato slotOccupato) {
        return new SlotResponse(slotOccupato.getId(), slotOccupato.getIdPrenotazione(), slotOccupato.getInizio(), slotOccupato.getFine());
    }

    public SlotOccupato toSlotOccupato(PrenotazioneEvento evento, Campo campo) {
        return new SlotOccupato(campo, evento.idPrenotazione(), evento.inizio(), evento.fine());
    }
}
