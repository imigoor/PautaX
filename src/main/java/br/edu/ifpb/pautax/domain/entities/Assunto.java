package br.edu.ifpb.pautax.domain.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Entity
@Data
public class Assunto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

    @NotBlank(message = "O nome do assunto é obrigatório.")
    @Size(min = 3, max = 50, message = "O nome do assunto deve ter entre 3 e 50 caracteres.")
    private String nome;

    @OneToMany(mappedBy = "assunto")
    @ToString.Exclude
    private List<Processo> processos;
}
