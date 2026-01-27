package br.edu.ifpb.pautax.domain.entities;

import br.edu.ifpb.pautax.domain.enums.StatusProcesso;
import br.edu.ifpb.pautax.domain.enums.TipoDecisao;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
public class Processo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String numero;
    private LocalDate dataRegistro;
    private LocalDate dataDistribuicao;
    private LocalDate dataParecer;

    @Lob
    @ToString.Exclude
    private byte[] requerimento;

    @Enumerated(EnumType.STRING)
    private TipoDecisao decisaoRelator;

    @Lob
    @Column(nullable = true)
    private String justificativaRelator;

    @Enumerated(EnumType.STRING)
    private StatusProcesso statusProcesso;

    @NotNull(message = "Selecione um assunto válido.")
    @ManyToOne
    @JoinColumn(name = "assunto_id")
    private Assunto assunto;

    @ManyToOne
    @JoinColumn(name = "relator_id")
    private Professor relator;

    @ManyToOne
    @JoinColumn(name = "aluno_id")
    private Aluno interessado;

    @ManyToOne
    @JoinColumn(name = "reuniao_id")
    private Reuniao reuniao;

    @OneToMany(mappedBy = "processo")
    @ToString.Exclude
    private List<Voto> votos;
}
