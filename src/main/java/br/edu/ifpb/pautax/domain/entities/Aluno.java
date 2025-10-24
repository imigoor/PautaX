package br.edu.ifpb.pautax.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Aluno extends Usuario{

    @Column(nullable = false)
    private String matricula;

    @OneToMany(mappedBy = "interessado")
    private List<Processo> processos;
}
