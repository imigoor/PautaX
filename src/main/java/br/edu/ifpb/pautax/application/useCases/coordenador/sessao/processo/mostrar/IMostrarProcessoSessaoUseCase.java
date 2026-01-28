package br.edu.ifpb.pautax.application.useCases.coordenador.sessao.processo.mostrar;

import org.springframework.web.servlet.ModelAndView;

public interface IMostrarProcessoSessaoUseCase {
    ModelAndView execute(Integer idSessao, Integer idProcesso);
}
