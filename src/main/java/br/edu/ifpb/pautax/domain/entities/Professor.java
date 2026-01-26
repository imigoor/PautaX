package br.edu.ifpb.pautax.domain.entities;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Professor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private boolean coordenador;

    @NotBlank(message = "A matrícula é obrigatória.")
    @Size(min = 4, max = 20, message = "A matrícula deve ter entre 4 e 20 caracteres.")
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
