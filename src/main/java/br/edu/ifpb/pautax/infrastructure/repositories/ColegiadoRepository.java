package br.edu.ifpb.pautax.infrastructure.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import br.edu.ifpb.pautax.domain.entities.Colegiado;

public interface ColegiadoRepository extends JpaRepository<Colegiado, Integer> {
}