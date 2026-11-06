package it.courtbook.notificationservice.dto;

import it.courtbook.notificationservice.model.TipoNotifica;

import java.time.LocalDateTime;
import java.util.Map;

public record NotificaResponse(Long id, Long idPrenotazione, TipoNotifica tipo, Map<String,Object> contenuto, LocalDateTime dataCreazione) {
}
