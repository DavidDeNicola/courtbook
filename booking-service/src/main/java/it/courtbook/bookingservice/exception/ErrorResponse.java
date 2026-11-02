package it.courtbook.bookingservice.exception;

import java.time.LocalDateTime;

public record ErrorResponse(LocalDateTime timestamp, int status, String messaggio) {
}
