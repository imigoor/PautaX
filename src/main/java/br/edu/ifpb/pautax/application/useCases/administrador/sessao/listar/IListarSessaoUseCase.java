package br.edu.ifpb.pautax.application.useCases.administrador.sessao.listar;

import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

public interface IListarSessaoUseCase {
    ModelAndView execute();
    void carregarDadosFormulario(Model model);
}
