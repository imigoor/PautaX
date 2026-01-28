package br.edu.ifpb.pautax.application.useCases.coordenador.sessao.listar;

import br.edu.ifpb.pautax.domain.enums.StatusReuniao;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

public interface IListarSessaoUseCase {
    ModelAndView execute(StatusReuniao status);
    void carregarDadosFormulario(Model model);
}
