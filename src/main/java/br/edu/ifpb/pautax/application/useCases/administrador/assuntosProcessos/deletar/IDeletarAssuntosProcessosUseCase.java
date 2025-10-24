package br.edu.ifpb.pautax.application.useCases.administrador.assuntosProcessos.deletar;

import org.springframework.web.servlet.ModelAndView;

public interface IDeletarAssuntosProcessosUseCase {
    public ModelAndView execute(Integer id);
}
