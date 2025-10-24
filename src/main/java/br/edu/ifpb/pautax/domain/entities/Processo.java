package br.edu.ifpb.pautax.domain.entities;

import br.edu.ifpb.pautax.domain.enums.StatusProcesso;
import br.edu.ifpb.pautax.domain.enums.TipoDecisao;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Data
public class Processo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String numero;
    private Date dataRecepcao;
    private Date dataDistribuicao;
    private Date dataParecer;

    @Lob
    private byte[] parecer;

    @Enumerated(EnumType.STRING)
    private TipoDecisao decisaoRelator;

    @Enumerated(EnumType.STRING)
    private StatusProcesso statusProcesso;

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
    private List<Voto> votos;
}
