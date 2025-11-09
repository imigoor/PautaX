package br.edu.ifpb.pautax.application.useCases.professor.processos.listar;

import org.springframework.web.servlet.ModelAndView;

import br.edu.ifpb.pautax.domain.entities.Usuario;

public interface IListarProcessosAtribuidosUseCase {

    public ModelAndView execute(Usuario professorLogado);
}
