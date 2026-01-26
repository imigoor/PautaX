package br.edu.ifpb.pautax.application.useCases.coordenador.sessao.mostrar;

import org.springframework.web.servlet.ModelAndView;

public interface IMostrarSessaoUseCase {
    ModelAndView execute(Integer idReuniao);
}
