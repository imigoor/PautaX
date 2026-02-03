package br.edu.ifpb.pautax.infrastructure.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.edu.ifpb.pautax.domain.entities.Professor;
import br.edu.ifpb.pautax.domain.entities.Usuario;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor,Integer> {
    Optional<Professor> findByUsuario(Usuario usuario);
    @Query("SELECT DISTINCT p FROM Professor p JOIN p.colegiados c")
    List<Professor> findAllMembrosDeColegiado();

    @Query("SELECT DISTINCT p FROM Professor p " +
           "JOIN p.colegiados c " +
           "JOIN c.membros m " +
           "WHERE m.id = :idCoordenador")
    List<Professor> findColegasDoCoordenador(int idCoordenador);

    Optional<Object> findByUsuarioLogin(String login);

    Optional<Object> findByUsuarioId(int professorId);
}
