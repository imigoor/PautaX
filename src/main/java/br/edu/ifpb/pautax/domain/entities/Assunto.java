package br.edu.ifpb.pautax.domain.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Assunto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;
    private String nome;

    @OneToMany(mappedBy = "assunto")
    private List<Processo> processos;
}
