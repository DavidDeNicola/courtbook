package it.courtbook.bookingservice.event;

import java.time.LocalDateTime;

public record PrenotazioneEvento(Long idPrenotazione, Long idUtente, Long idCampo, LocalDateTime inizio, LocalDateTime fine) {
}
