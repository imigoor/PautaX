package br.edu.ifpb.pautax.application.useCases.coordenador.processo.listarPendentes;

import br.edu.ifpb.pautax.domain.entities.Processo;
import br.edu.ifpb.pautax.infrastructure.repositories.ProcessoRepository;
import br.edu.ifpb.pautax.infrastructure.repositories.ProfessorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ListarProcessosPendentesUseCase implements IListarProcessosPendentesUseCase {
    private final ProcessoRepository processoRepository;
    private final ProfessorRepository professorRepository;

    @Override
    @Transactional
    public ModelAndView execute() {
        ModelAndView modelAndView = new ModelAndView("coordenador/listar-nao-distribuidos");

        List<Processo> processos = processoRepository.findByRelatorIsNull();

        modelAndView.addObject("processosPendentes", processos);
        modelAndView.addObject("listaProfessores", professorRepository.findAllMembrosDeColegiado());

        return modelAndView;
    }
}
