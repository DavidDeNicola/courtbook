package it.courtbook.availabilityservice.service;

import it.courtbook.availabilityservice.dto.CampoRequest;
import it.courtbook.availabilityservice.dto.CampoResponse;
import it.courtbook.availabilityservice.dto.SlotResponse;
import it.courtbook.availabilityservice.exception.CampoGiaPresenteException;
import it.courtbook.availabilityservice.exception.CampoNonTrovatoException;
import it.courtbook.availabilityservice.mapper.CampoMapper;
import it.courtbook.availabilityservice.mapper.SlotOccupatoMapper;
import it.courtbook.availabilityservice.repository.CampoRepository;
import it.courtbook.availabilityservice.repository.SlotOccupatoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CampoService {

    private final CampoRepository campoRepository;
    private final SlotOccupatoRepository slotOccupatoRepository;
    private final CampoMapper campoMapper;
    private final SlotOccupatoMapper slotOccupatoMapper;

    public CampoResponse crea(CampoRequest request) {
        if (campoRepository.existsByNome(request.nome())) {
            throw new CampoGiaPresenteException("Campo con nome " + request.nome() + " già esistente");
        }

        return campoMapper.toResponse(campoRepository.save(campoMapper.fromRequest(request)));
    }

    public List<CampoResponse> trovaTutti() {
        return campoRepository.findAll().stream().map(campoMapper::toResponse).toList();
    }

    public CampoResponse trovaPerId(Long id) {
        return campoMapper.toResponse(campoRepository.findById(id).orElseThrow(() -> new CampoNonTrovatoException("Campo non trovato")));
    }

    public List<SlotResponse> slotOccupati(Long campoId, LocalDate data) {
        campoRepository.findById(campoId).orElseThrow(() -> new CampoNonTrovatoException("Campo non trovato"));
        return slotOccupatoRepository.findByCampoIdAndInizioBetween(campoId, data.atStartOfDay(), data.plusDays(1).atStartOfDay()).stream().map(slotOccupatoMapper::toResponse).toList();
    }

}
