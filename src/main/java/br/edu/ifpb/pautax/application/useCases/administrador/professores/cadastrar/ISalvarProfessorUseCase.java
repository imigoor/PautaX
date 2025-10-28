package br.edu.ifpb.pautax.application.useCases.administrador.professores.cadastrar;

import br.edu.ifpb.pautax.domain.entities.Professor;

public interface ISalvarProfessorUseCase {
    String execute(Professor professor);
}
