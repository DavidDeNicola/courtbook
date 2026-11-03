package it.courtbook.availabilityservice.dto;

import java.time.LocalDateTime;

public record SlotResponse(Long id, Long idPrenotazione, LocalDateTime inizio, LocalDateTime fine) {
}
