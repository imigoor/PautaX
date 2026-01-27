package br.edu.ifpb.pautax.infrastructure.repositories;

import br.edu.ifpb.pautax.domain.entities.Reuniao;
import br.edu.ifpb.pautax.domain.enums.StatusReuniao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ReuniaoRepository extends JpaRepository<Reuniao, Integer> {
    boolean existsByStatusAndIdNot(StatusReuniao status, Integer id);

    List<Reuniao> findByStatus(StatusReuniao status);
}
