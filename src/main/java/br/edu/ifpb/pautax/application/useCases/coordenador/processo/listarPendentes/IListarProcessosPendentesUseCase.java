package br.edu.ifpb.pautax.application.useCases.coordenador.processo.listarPendentes;

import org.springframework.data.domain.Pageable;
import org.springframework.web.servlet.ModelAndView;

import br.edu.ifpb.pautax.infrastructure.security.CustomUserDetails;

public interface IListarProcessosPendentesUseCase {
    ModelAndView execute(Pageable pageable, CustomUserDetails userDetail);
}
