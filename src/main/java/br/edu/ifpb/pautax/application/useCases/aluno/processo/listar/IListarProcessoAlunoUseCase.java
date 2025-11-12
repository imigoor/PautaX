package br.edu.ifpb.pautax.application.useCases.aluno.processo.listar;

import br.edu.ifpb.pautax.infrastructure.security.CustomUserDetails;
import org.springframework.web.servlet.ModelAndView;

public interface IListarProcessoAlunoUseCase {
    ModelAndView execute(CustomUserDetails userDetails);
}
