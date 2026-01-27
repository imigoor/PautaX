package br.edu.ifpb.pautax.application.useCases.administrador.professores.sessao.processo.visualizar;

import org.springframework.web.servlet.ModelAndView;

public interface IVisualizarProcessoVotacaoUseCase {
    ModelAndView execute(Integer processoId);
}