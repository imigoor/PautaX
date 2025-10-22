package br.edu.ifpb.pautax.domain.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Professor extends Usuario{
    private boolean coordenador;

    @ManyToMany(mappedBy = "membros")
    private List<Colegiado> colegiados;

    @OneToMany(mappedBy = "relator")
    private List<Processo> processosRelatados;

    @OneToMany(mappedBy = "professor")
    private List<Voto> votos;
}
