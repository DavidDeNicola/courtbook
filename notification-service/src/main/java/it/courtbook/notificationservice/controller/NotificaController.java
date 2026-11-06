package it.courtbook.notificationservice.controller;

import it.courtbook.notificationservice.dto.NotificaResponse;
import it.courtbook.notificationservice.service.NotificaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/notifiche")
@RequiredArgsConstructor
public class NotificaController {

    private final NotificaService notificaService;

    @GetMapping("/utente/{idUtente}")
    public ResponseEntity<List<NotificaResponse>> trovaPerUtente(@PathVariable Long idUtente) {
        return ResponseEntity.ok(notificaService.trovaPerUtente(idUtente));
    }


}
