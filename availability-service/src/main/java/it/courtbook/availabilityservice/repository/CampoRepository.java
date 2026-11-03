package it.courtbook.availabilityservice.repository;

import it.courtbook.availabilityservice.model.Campo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CampoRepository extends JpaRepository<Campo, Long> {

    boolean existsByNome(String nome);
}
