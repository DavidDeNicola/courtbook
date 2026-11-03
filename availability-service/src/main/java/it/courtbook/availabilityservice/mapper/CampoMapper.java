package it.courtbook.availabilityservice.mapper;

import it.courtbook.availabilityservice.dto.CampoRequest;
import it.courtbook.availabilityservice.dto.CampoResponse;
import it.courtbook.availabilityservice.model.Campo;
import org.springframework.stereotype.Component;

@Component
public class CampoMapper {

    public CampoResponse toResponse(Campo campo) {
        return new CampoResponse(campo.getId(), campo.getNome(), campo.getTipo());
    }

    public Campo fromRequest(CampoRequest request) {
        return new Campo(request.nome(), request.tipo());
    }
}
