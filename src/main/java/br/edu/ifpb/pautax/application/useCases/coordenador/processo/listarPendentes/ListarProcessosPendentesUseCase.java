package br.edu.ifpb.pautax.application.useCases.coordenador.processo.listarPendentes;

import br.edu.ifpb.pautax.domain.entities.Processo;
import br.edu.ifpb.pautax.infrastructure.repositories.ProcessoRepository;
import br.edu.ifpb.pautax.infrastructure.repositories.ProfessorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

@Service
@RequiredArgsConstructor
public class ListarProcessosPendentesUseCase implements IListarProcessosPendentesUseCase {
    private final ProcessoRepository processoRepository;
    private final ProfessorRepository professorRepository;

    @Override
    @Transactional
    public ModelAndView execute(Pageable pageable) {
        ModelAndView modelAndView = new ModelAndView("coordenador/listar-nao-distribuidos");

        Page<Processo> processos = processoRepository.findByRelatorIsNull(pageable);

        modelAndView.addObject("processosPendentes", processos);
        modelAndView.addObject("listaProfessores", professorRepository.findAllMembrosDeColegiado());

        return modelAndView;
    }
}
