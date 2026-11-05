package it.courtbook.notificationservice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import java.time.LocalDateTime;
import java.util.Map;

@Entity
@Table(name = "notifiche", uniqueConstraints =  { @UniqueConstraint(columnNames = {"id_prenotazione","tipo"})})
@Getter
@Setter
@NoArgsConstructor
public class Notifica {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long idUtente;

    @Column(nullable = false)
    private Long idPrenotazione;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoNotifica tipo;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    private Map<String,Object> contenuto;

    @CreationTimestamp
    @Column(updatable = false, nullable = false)
    private LocalDateTime dataCreazione = LocalDateTime.now();
}
