package br.edu.ifpb.pautax.domain.entities;

import br.edu.ifpb.pautax.domain.enums.StatusReuniao;
import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Entity
@Data
public class Reuniao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull(message = "A data da reunião é obrigatória.")
    @Future(message = "A data deve estar no futuro.")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataReuniao;

    @Enumerated(EnumType.STRING)
    private StatusReuniao status;

    @Lob
    @ToString.Exclude
    private byte[] ata;

    @NotNull(message = "Você deve selecionar um colegiado.")
    @ManyToOne
    @JoinColumn(name = "colegiado_id")
    @ToString.Exclude
    private Colegiado colegiado;

    @OneToMany(mappedBy = "reuniao")
    @ToString.Exclude
    private List<Processo> processos;

    @NotEmpty(message = "A pauta não pode estar vazia. Selecione pelo menos um processo.")
    @Transient
    private List<Integer> processosIds;
}
