package br.edu.ifpb.pautax.domain.entities;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Colegiado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Date dataInicio;
    private Date dataFim;
    private String descricao;
    private String portaria;
    private String curso;

    @OneToMany(mappedBy = "colegiado")
    private List<Reuniao> reunioes;

    @ManyToMany
    @JoinTable(
            name = "colegiado_professor",
            joinColumns = @JoinColumn(name = "colegiado_id"),
            inverseJoinColumns = @JoinColumn(name = "professor_id")
    )
    private List<Professor> membros;
}
