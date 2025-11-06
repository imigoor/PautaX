package br.edu.ifpb.pautax.application.useCases.administrador.colegiados.editar;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import br.edu.ifpb.pautax.infrastructure.repositories.ColegiadoRepository;
import br.edu.ifpb.pautax.infrastructure.repositories.ProfessorRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EditarColegiadoUseCase implements IEditarColegiadoUseCase {
    private final ColegiadoRepository colegiadoRepository;
    private final ProfessorRepository professorRepository;

    @Override
    public ModelAndView execute(Integer id) {
        ModelAndView modelAndView = new ModelAndView("administrador/gerenciar-colegiados");

        modelAndView.addObject("colegiado", colegiadoRepository.findById(id).get());
        modelAndView.addObject("listaDeColegiados", colegiadoRepository.findAll());
        modelAndView.addObject("todosProfessores", professorRepository.findAll());

        return modelAndView;
    }
}
