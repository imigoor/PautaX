package br.edu.ifpb.pautax.application.useCases.administrador.assuntosProcessos.editar;

import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.web.servlet.ModelAndView;

public interface IEditarAssuntosProcessosUseCase {
    public ModelAndView execute(Integer id);
}
