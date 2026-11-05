package it.courtbook.bookingservice.event;

public record EsitoEvento(Long idPrenotazione, Long idUtente, Long idCampo, Esito esito, String motivo) {
}
