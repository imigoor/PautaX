package br.edu.ifpb.pautax.infrastructure.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import br.edu.ifpb.pautax.domain.entities.Colegiado;

import java.util.List;

public interface ColegiadoRepository extends JpaRepository<Colegiado, Integer> {
    List<Colegiado> findAllByMembros_Id(int id);
}