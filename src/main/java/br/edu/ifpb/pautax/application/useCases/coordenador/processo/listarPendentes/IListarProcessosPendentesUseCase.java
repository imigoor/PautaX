package br.edu.ifpb.pautax.application.useCases.coordenador.processo.listarPendentes;

import org.springframework.data.domain.Pageable;
import org.springframework.web.servlet.ModelAndView;

public interface IListarProcessosPendentesUseCase {
    ModelAndView execute(Pageable pageable);
}
