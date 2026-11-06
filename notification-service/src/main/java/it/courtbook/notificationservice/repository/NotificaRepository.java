package it.courtbook.notificationservice.repository;

import it.courtbook.notificationservice.model.Notifica;
import it.courtbook.notificationservice.model.TipoNotifica;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificaRepository extends JpaRepository<Notifica, Long> {

    boolean existsByIdPrenotazioneAndTipo(Long idPrenotazione, TipoNotifica tipo);

    List<Notifica> findByIdUtenteOrderByDataCreazioneDesc(Long idUtente);
}
