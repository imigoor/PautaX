package br.edu.ifpb.pautax.application.useCases.administrador.colegiados.listar;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import br.edu.ifpb.pautax.domain.entities.Colegiado;
import br.edu.ifpb.pautax.infrastructure.repositories.ColegiadoRepository;
import br.edu.ifpb.pautax.infrastructure.repositories.ProfessorRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ListarColegiadoUseCase implements IListarColegiadoUseCase {
    private final ColegiadoRepository colegiadoRepository;
    private final ProfessorRepository professorRepository;

    @Override
    public ModelAndView execute() {
        ModelAndView modelAndView = new ModelAndView("administrador/gerenciar-colegiados");

        modelAndView.addObject("listaDeColegiados", colegiadoRepository.findAll());
        modelAndView.addObject("colegiado", new Colegiado());
        modelAndView.addObject("todosProfessores", professorRepository.findAll());

        return modelAndView;
    }
}
