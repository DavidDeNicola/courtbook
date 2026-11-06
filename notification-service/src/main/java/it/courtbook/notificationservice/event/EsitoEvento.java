package it.courtbook.notificationservice.event;

public record EsitoEvento(Long idPrenotazione, Long idUtente, Long idCampo, Esito esito, String motivo) {
}
