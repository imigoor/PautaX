package br.edu.ifpb.pautax.application.useCases.administrador.assuntosProcesso;

import br.edu.ifpb.pautax.domain.entities.Assunto;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

public interface IlistarAssuntosProcessosUseCase {
    public ModelAndView execute();
}
