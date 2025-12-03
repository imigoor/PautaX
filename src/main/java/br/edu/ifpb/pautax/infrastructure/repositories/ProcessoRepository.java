package br.edu.ifpb.pautax.infrastructure.repositories;

import br.edu.ifpb.pautax.domain.entities.Aluno;
import br.edu.ifpb.pautax.domain.entities.Processo;
import br.edu.ifpb.pautax.domain.entities.Professor;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ProcessoRepository extends JpaRepository<Processo, Integer>, JpaSpecificationExecutor<Processo> {

    List<Processo> findByRelator(Professor findByRelator);
    List<Processo> findByRelatorIsNull();

    List<Processo> findAllByInteressado(Aluno aluno);
}
