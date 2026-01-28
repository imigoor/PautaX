package br.edu.ifpb.pautax.domain.entities;

import br.edu.ifpb.pautax.domain.enums.TipoVoto;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Voto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.STRING)
    private TipoVoto voto;

    @Lob
    @Column(nullable = true)
    private String justificativa;

    private boolean ausente;

    @ManyToOne
    @JoinColumn(name = "professor_id")
    private Professor professor;

    @ManyToOne
    @JoinColumn(name = "processo_id")
    private Processo processo;
}
