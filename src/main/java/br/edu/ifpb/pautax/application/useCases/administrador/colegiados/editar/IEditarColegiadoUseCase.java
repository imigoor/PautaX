package br.edu.ifpb.pautax.application.useCases.administrador.colegiados.editar;

import org.springframework.web.servlet.ModelAndView;

public interface IEditarColegiadoUseCase {
    public ModelAndView execute(Integer id);
}
