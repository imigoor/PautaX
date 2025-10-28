package br.edu.ifpb.pautax.application.useCases.administrador.professores.editar;

import br.edu.ifpb.pautax.domain.entities.Professor;
import br.edu.ifpb.pautax.infrastructure.repositories.ProfessorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

@Service
@RequiredArgsConstructor
public class EditarProfessorUseCase implements  IEditarProfessorUseCase{
    private final ProfessorRepository professorRepository;

    @Override
    public ModelAndView execute(int id) {
        ModelAndView modelAndView = new ModelAndView("administrador/gerenciar-professores");
        modelAndView.addObject("professor", professorRepository.findById(id).orElse(new Professor()));
        modelAndView.addObject("listaDeProfessores", professorRepository.findAll());
        return modelAndView;
    }
}
