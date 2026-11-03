package it.courtbook.availabilityservice.dto;

import it.courtbook.availabilityservice.model.TipoCampo;

public record CampoResponse(Long id, String nome, TipoCampo tipo) {
}
