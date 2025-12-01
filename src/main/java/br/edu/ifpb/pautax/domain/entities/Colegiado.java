package br.edu.ifpb.pautax.domain.entities;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;
import java.util.List;

import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Data
public class Colegiado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dataInicio;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
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
    @ToString.Exclude
    private List<Professor> membros;
}
