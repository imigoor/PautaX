package br.edu.ifpb.pautax.application.useCases.administrador.assuntosProcessos.deletar;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public interface IDeletarAssuntosProcessosUseCase {
    public String execute(Integer id, RedirectAttributes redirectAttributes);
}
