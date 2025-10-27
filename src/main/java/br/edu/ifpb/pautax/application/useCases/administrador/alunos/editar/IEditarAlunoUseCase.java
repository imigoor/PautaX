package br.edu.ifpb.pautax.application.useCases.administrador.alunos.editar;

import org.springframework.web.servlet.ModelAndView;

public interface IEditarAlunoUseCase {
    ModelAndView execute(int id);
}
