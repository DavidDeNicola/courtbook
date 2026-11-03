package it.courtbook.availabilityservice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "slot_occupati", uniqueConstraints = @UniqueConstraint(columnNames = {"campo_id", "inizio"}))
public class SlotOccupato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "campo_id", nullable = false)
    private Campo campo;

    @Column(nullable = false, unique = true)
    private Long idPrenotazione;

    @Column(nullable = false)
    private LocalDateTime inizio;

    @Column(nullable = false)
    private LocalDateTime fine;
}
