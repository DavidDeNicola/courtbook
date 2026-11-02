package it.courtbook.bookingservice.exception;

public class PrenotazioneNonTrovataException extends RuntimeException {
    public PrenotazioneNonTrovataException(String message) {
        super(message);
    }
}
