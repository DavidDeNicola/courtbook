package it.courtbook.bookingservice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor

@Table(name = "prenotazioni")
public class Prenotazione {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long idUtente;

    @Column(nullable = false)
    private Long idCampo;

    @Column(nullable = false)
    private LocalDateTime inizio;

    @Column(nullable = false)
    private LocalDateTime fine;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StatoPrenotazione stato = StatoPrenotazione.PENDING;

    public Prenotazione(Long idUtente, Long idCampo, LocalDateTime inizio, LocalDateTime fine) {
        this.idUtente = idUtente;
        this.idCampo = idCampo;
        this.inizio = inizio;
        this.fine = fine;
    }
}
