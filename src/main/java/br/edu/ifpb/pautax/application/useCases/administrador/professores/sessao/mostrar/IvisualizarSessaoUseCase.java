package br.edu.ifpb.pautax.application.useCases.administrador.professores.sessao.mostrar;

import org.springframework.web.servlet.ModelAndView;

public interface IvisualizarSessaoUseCase {
    ModelAndView execute(Integer sessaoId);
}
