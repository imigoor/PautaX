package br.edu.ifpb.pautax.infrastructure.repositories;

import br.edu.ifpb.pautax.domain.entities.Reuniao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReuniaoRepository extends JpaRepository<Reuniao, Integer> {
}
