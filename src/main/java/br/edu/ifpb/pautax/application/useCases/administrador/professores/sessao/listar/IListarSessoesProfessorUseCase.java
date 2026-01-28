package br.edu.ifpb.pautax.application.useCases.administrador.professores.sessao.listar;

import br.edu.ifpb.pautax.domain.enums.StatusReuniao;
import org.springframework.web.servlet.ModelAndView;

public interface IListarSessoesProfessorUseCase {
    ModelAndView execute(StatusReuniao status);
}