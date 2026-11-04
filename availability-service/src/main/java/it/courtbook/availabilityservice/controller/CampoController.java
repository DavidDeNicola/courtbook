package it.courtbook.availabilityservice.controller;

import it.courtbook.availabilityservice.dto.CampoRequest;
import it.courtbook.availabilityservice.dto.CampoResponse;
import it.courtbook.availabilityservice.dto.SlotResponse;
import it.courtbook.availabilityservice.service.CampoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/campi")
@RequiredArgsConstructor
public class CampoController {

    private final CampoService campoService;

    @PostMapping
    public ResponseEntity<CampoResponse> crea(@Valid @RequestBody CampoRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(campoService.crea(request));
    }

    @GetMapping
    public ResponseEntity<List<CampoResponse>> trovaTutti() {
        return ResponseEntity.ok(campoService.trovaTutti());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CampoResponse> trovaPerId(@PathVariable Long id) {
        return ResponseEntity.ok(campoService.trovaPerId(id));
    }

    @GetMapping("/{id}/slot")
    public ResponseEntity<List<SlotResponse>> slotOccupati(@PathVariable Long id, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data) {
        return ResponseEntity.ok(campoService.slotOccupati(id, data));
    }

}
