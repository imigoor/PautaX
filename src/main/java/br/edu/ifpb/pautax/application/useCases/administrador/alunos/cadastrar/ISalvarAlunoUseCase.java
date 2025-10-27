package br.edu.ifpb.pautax.application.useCases.administrador.alunos.cadastrar;

import br.edu.ifpb.pautax.domain.entities.Aluno;

public interface ISalvarAlunoUseCase {
    String execute(Aluno aluno);
}
