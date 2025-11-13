package br.edu.ifpb.pautax.domain.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Entity
@Data
public class Professor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private boolean coordenador;

    @Column(nullable = false)
    private String matricula;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "usuario_id", nullable = false)
    Usuario usuario;

    @ManyToMany(mappedBy = "membros")
    private List<Colegiado> colegiados;

    @OneToMany(mappedBy = "relator")
    private List<Processo> processosRelatados;

    @OneToMany(mappedBy = "professor")
    private List<Voto> votos;
}
