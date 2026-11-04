package it.courtbook.bookingservice.dto;

import it.courtbook.bookingservice.model.StatoPrenotazione;

import java.time.LocalDateTime;

public record PrenotazioneResponse(Long id, Long idUtente, Long idCampo, LocalDateTime inizio, LocalDateTime fine, StatoPrenotazione stato) {
}
