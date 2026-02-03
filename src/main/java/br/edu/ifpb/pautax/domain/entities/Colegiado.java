package br.edu.ifpb.pautax.domain.entities;

import br.edu.ifpb.pautax.domain.validators.PeriodoValido;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import java.util.Date;
import java.util.List;

import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Data
@PeriodoValido
public class Colegiado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull(message = "A data de início é obrigatória.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dataInicio;

    @NotNull(message = "A data de fim é obrigatória.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dataFim;

    @NotBlank(message = "A descrição é obrigatória.")
    @Size(min = 5, message = "A descrição deve ter no mínimo 5 caracteres.")
    private String descricao;

    @NotBlank(message = "A portaria é obrigatória.")
    @Size(min = 5, message = "A portaria deve ter no mínimo 5 caracteres.")
    private String portaria;

    @NotBlank(message = "O curso é obrigatório.")
    @Size(min = 5, message = "O curso deve ter no mínimo 5 caracteres.")
    private String curso;

    @NotNull(message = "É obrigatório selecionar um aluno representante.")
    @ManyToOne
    @JoinColumn(name = "aluno_id")
    private Aluno representante;

    @OneToMany(mappedBy = "colegiado")
    private List<Reuniao> reunioes;

    @ManyToMany
    @JoinTable(
            name = "colegiado_professor",
            joinColumns = @JoinColumn(name = "colegiado_id"),
            inverseJoinColumns = @JoinColumn(name = "professor_id")
    )
    @ToString.Exclude
    private List<Professor> membros;
}
