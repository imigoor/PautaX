package br.edu.ifpb.pautax.domain.entities;

import br.edu.ifpb.pautax.domain.enums.StatusReuniao;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Data
public class Reuniao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Date dataReuniao;

    @Enumerated(EnumType.STRING)
    private StatusReuniao status;

    @Lob
    private byte[] ata;

    @ManyToOne
    @JoinColumn(name = "colegiado_id")
    private Colegiado colegiado;

    @OneToMany(mappedBy = "reuniao")
    private List<Processo> processos;
}
