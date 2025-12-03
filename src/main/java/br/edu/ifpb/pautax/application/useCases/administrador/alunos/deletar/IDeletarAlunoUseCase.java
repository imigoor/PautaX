package br.edu.ifpb.pautax.application.useCases.administrador.alunos.deletar;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public interface IDeletarAlunoUseCase {
    String execute(int id, RedirectAttributes attributes);
}
