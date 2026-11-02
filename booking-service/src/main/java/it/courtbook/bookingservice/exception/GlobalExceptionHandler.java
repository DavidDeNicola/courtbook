package it.courtbook.bookingservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> prenotazioneNonTrovataException(PrenotazioneNonTrovataException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(LocalDateTime.now(), 404, e.getMessage()));
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> prenotazioneNonCreabileException(PrenotazioneNonCreabileException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(LocalDateTime.now(), 400, e.getMessage()));
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> prenotazioneNonCancellabileException(PrenotazioneNonCancellabileException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorResponse(LocalDateTime.now(), 409, e.getMessage()));
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> validazione(MethodArgumentNotValidException e) {
        String messaggio = e.getBindingResult().getFieldErrors().stream()
                .map(err -> err.getField() + ": " + err.getDefaultMessage())
                .collect(Collectors.joining(", "));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse(LocalDateTime.now(), 400, messaggio));
    }
}
