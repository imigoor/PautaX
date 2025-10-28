package br.edu.ifpb.pautax.application.useCases.administrador.professores.listar;

import br.edu.ifpb.pautax.domain.entities.Professor;
import br.edu.ifpb.pautax.infrastructure.repositories.ProfessorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

@Service
@RequiredArgsConstructor
public class ListarProfessorUseCase implements IListarProfessorUseCase{
    private final ProfessorRepository professorRepository;

    @Override
    public ModelAndView execute() {
        ModelAndView modelAndView = new ModelAndView("administrador/gerenciar-professores");
        modelAndView.addObject("professor", new Professor());
        modelAndView.addObject("listaDeProfessores", professorRepository.findAll());
        return modelAndView;
    }
}
