package it.courtbook.availabilityservice.repository;

import it.courtbook.availabilityservice.model.SlotOccupato;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface SlotOccupatoRepository extends JpaRepository<SlotOccupato, Long> {

    List<SlotOccupato> findByCampoIdAndInizioBetween(Long campoId, LocalDateTime da, LocalDateTime a);

    boolean existsByCampoIdAndInizioLessThanAndFineGreaterThan(Long campoId, LocalDateTime fine, LocalDateTime inizio);

    boolean existsByIdPrenotazione(Long idPrenotazione);
}
