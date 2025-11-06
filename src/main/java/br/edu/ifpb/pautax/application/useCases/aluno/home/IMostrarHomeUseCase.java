package br.edu.ifpb.pautax.application.useCases.aluno.home;

import br.edu.ifpb.pautax.infrastructure.security.CustomUserDetails;
import org.springframework.web.servlet.ModelAndView;

public interface IMostrarHomeUseCase {
    public ModelAndView execute(CustomUserDetails userDetails);
}
