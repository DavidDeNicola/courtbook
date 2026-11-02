package it.courtbook.bookingservice.exception;

public class PrenotazioneNonCancellabileException extends RuntimeException {
    public PrenotazioneNonCancellabileException(String message) {
        super(message);
    }
}
