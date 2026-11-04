package it.courtbook.availabilityservice.exception;

import java.time.LocalDateTime;

public record ErrorResponse(LocalDateTime timestamp, int status, String messaggio) {
}
