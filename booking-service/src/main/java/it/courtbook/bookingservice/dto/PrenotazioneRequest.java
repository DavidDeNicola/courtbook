package it.courtbook.bookingservice.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record PrenotazioneRequest(@NotNull Long idUtente, @NotNull Long idCampo, @NotNull @Future LocalDateTime inizio, @NotNull LocalDateTime fine) {
}
