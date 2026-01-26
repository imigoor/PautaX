package br.edu.ifpb.pautax.application.useCases.professor.processos.parecer;

import org.springframework.web.servlet.ModelAndView;

public interface IExibirFormularioParecerUseCase {
    ModelAndView execute(Integer idProcesso);
}
