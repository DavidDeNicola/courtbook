package it.courtbook.notificationservice.mapper;

import it.courtbook.notificationservice.dto.NotificaResponse;
import it.courtbook.notificationservice.event.EsitoEvento;
import it.courtbook.notificationservice.event.PrenotazioneEvento;
import it.courtbook.notificationservice.model.Notifica;
import it.courtbook.notificationservice.model.TipoNotifica;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class NotificaMapper {

    public Notifica fromEsito(EsitoEvento evento) {
        TipoNotifica tipo = switch (evento.esito()) {
            case CONFERMATA -> TipoNotifica.CONFERMATA;
            case RIFIUTATA -> TipoNotifica.RIFIUTATA;
        };

        Map<String, Object> contenuto = Map.of(
                "idCampo", evento.idCampo(),
                "motivo", evento.motivo()
        );

        return new Notifica(evento.idUtente(), evento.idPrenotazione(), tipo, contenuto);
    }

    public Notifica fromCancellazione(PrenotazioneEvento evento) {
        Map<String, Object> contenuto = Map.of(
                "idCampo", evento.idCampo(),
                "inizio", evento.inizio().toString(),
                "fine", evento.fine().toString()
        );

        return new Notifica(evento.idUtente(), evento.idPrenotazione(), TipoNotifica.CANCELLATA, contenuto);
    }

    public NotificaResponse toResponse(Notifica notifica) {
        return new NotificaResponse(
                notifica.getId(),
                notifica.getIdPrenotazione(),
                notifica.getTipo(),
                notifica.getContenuto(),
                notifica.getDataCreazione()
        );
    }
}
