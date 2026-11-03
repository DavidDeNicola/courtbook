package it.courtbook.availabilityservice.dto;

import it.courtbook.availabilityservice.model.TipoCampo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CampoRequest(@NotBlank String nome, @NotNull TipoCampo tipo) {
}
