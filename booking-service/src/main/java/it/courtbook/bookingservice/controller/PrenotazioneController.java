package it.courtbook.bookingservice.controller;

import it.courtbook.bookingservice.dto.PrenotazioneRequest;
import it.courtbook.bookingservice.dto.PrenotazioneResponse;
import it.courtbook.bookingservice.service.PrenotazioneService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/prenotazioni")
@RequiredArgsConstructor
public class PrenotazioneController {

    private final PrenotazioneService prenotazioneService;


    @PostMapping
    public ResponseEntity<PrenotazioneResponse> crea(@Valid @RequestBody PrenotazioneRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(prenotazioneService.crea(request));
    }

    @GetMapping
    public ResponseEntity<List<PrenotazioneResponse>> lista() {
        return ResponseEntity.ok(prenotazioneService.trovaTutte());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PrenotazioneResponse> dettaglio(@PathVariable Long id) {
        return ResponseEntity.ok(prenotazioneService.trovaPerId(id));
    }

    @PatchMapping("/{id}/cancella")
    public ResponseEntity<PrenotazioneResponse> cancella(@PathVariable Long id) {
        return ResponseEntity.ok(prenotazioneService.cancella(id));
    }
}
