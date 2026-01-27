package br.edu.ifpb.pautax.infrastructure.repositories;

import br.edu.ifpb.pautax.domain.entities.Professor;
import br.edu.ifpb.pautax.domain.entities.Reuniao;
import br.edu.ifpb.pautax.domain.enums.StatusReuniao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReuniaoRepository extends JpaRepository<Reuniao, Integer> {
    boolean existsByStatusAndIdNot(StatusReuniao status, Integer id);

    List<Reuniao> findByColegiadoMembrosContains(Professor professor);
}
