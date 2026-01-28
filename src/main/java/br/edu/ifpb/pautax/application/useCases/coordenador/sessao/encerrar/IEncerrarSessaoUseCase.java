package br.edu.ifpb.pautax.application.useCases.coordenador.sessao.encerrar;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public interface IEncerrarSessaoUseCase {
    String execute(Integer idSessao, RedirectAttributes redirectAttributes);
}
