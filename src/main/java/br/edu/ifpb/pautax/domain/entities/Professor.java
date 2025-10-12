package br.edu.ifpb.pautax.domain.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Professor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nome;
    private String fone;
    private String matricula;
    private String login;
    private String senha;
    private boolean coordenador;

    @ManyToMany(mappedBy = "membros")
    private List<Colegiado> colegiados;

    @OneToMany(mappedBy = "relator")
    private List<Processo> processosRelatados;

    @OneToMany(mappedBy = "professor")
    private List<Voto> votos;
}
