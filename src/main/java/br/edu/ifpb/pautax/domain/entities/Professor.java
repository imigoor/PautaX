package br.edu.ifpb.pautax.domain.entities;

import br.edu.ifpb.pautax.domain.validators.matricula.IMatricula;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Entity
@Data
public class Professor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private boolean coordenador;

    @IMatricula
    @Column(nullable = false)
    private String matricula;

    @Valid
    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "usuario_id", nullable = false)
    Usuario usuario;

    @ManyToMany(mappedBy = "membros")
    @ToString.Exclude
    private List<Colegiado> colegiados;

    @OneToMany(mappedBy = "relator")
    @ToString.Exclude
    private List<Processo> processosRelatados;

    @OneToMany(mappedBy = "professor")
    private List<Voto> votos;
}
