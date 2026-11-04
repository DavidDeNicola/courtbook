package it.courtbook.availabilityservice.service;

import it.courtbook.availabilityservice.event.Esito;
import it.courtbook.availabilityservice.event.EsitoEventi;
import it.courtbook.availabilityservice.event.EsitoEvento;
import it.courtbook.availabilityservice.event.PrenotazioneEvento;
import it.courtbook.availabilityservice.mapper.SlotOccupatoMapper;
import it.courtbook.availabilityservice.model.Campo;
import it.courtbook.availabilityservice.repository.CampoRepository;
import it.courtbook.availabilityservice.repository.SlotOccupatoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SlotService {

    private final SlotOccupatoRepository slotOccupatoRepository;
    private final SlotOccupatoMapper slotOccupatoMapper;
    private final CampoRepository campoRepository;
    private final EsitoEventi esitoEventi;

    public void gestisciRichiesta(PrenotazioneEvento evento) {

        if (slotOccupatoRepository.existsByIdPrenotazione(evento.idPrenotazione())) {
            return;
        }

        Optional<Campo> campo = campoRepository.findById(evento.idCampo());

        if(campo.isEmpty()){
            esitoEventi.inviaEsito(new EsitoEvento(evento.idPrenotazione(), evento.idUtente(), evento.idCampo(), Esito.RIFIUTATA, "Campo inesistente"));
            return;
        }

        if(slotOccupatoRepository.existsByCampoIdAndInizioLessThanAndFineGreaterThan(evento.idCampo(), evento.fine(), evento.inizio())){
            esitoEventi.inviaEsito(new EsitoEvento(evento.idPrenotazione(), evento.idUtente(), evento.idCampo(), Esito.RIFIUTATA, "Slot gia' occupato"));
            return;
        }

        slotOccupatoRepository.save(slotOccupatoMapper.toSlotOccupato(evento, campo.get()));
        esitoEventi.inviaEsito(new EsitoEvento(evento.idPrenotazione(), evento.idUtente(), evento.idCampo(), Esito.CONFERMATA, "Prenotazione confermata"));
    }


}
