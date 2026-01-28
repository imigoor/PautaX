package br.edu.ifpb.pautax.infrastructure.repositories;

import br.edu.ifpb.pautax.domain.entities.Voto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VotoRepository extends JpaRepository<Voto, Integer> {
}
