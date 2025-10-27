package br.edu.ifpb.pautax.application.useCases.administrador.professores.editar;

import org.springframework.web.servlet.ModelAndView;

public interface IEditarProfessorUseCase {
    ModelAndView execute(int id);
}
