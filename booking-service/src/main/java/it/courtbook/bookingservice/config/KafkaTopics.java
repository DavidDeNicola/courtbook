package it.courtbook.bookingservice.config;

public final class KafkaTopics {

    public static final String TOPIC_PRENOTAZIONE_CREATA = "prenotazione-creata";
    public static final String TOPIC_PRENOTAZIONE_CANCELLATA = "prenotazione-cancellata";
    public static final String TOPIC_PRENOTAZIONE_ESITO = "prenotazione-esito";

    private KafkaTopics() {
    }
}
