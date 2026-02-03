package br.edu.ifpb.pautax.application.useCases.coordenador.processo.listarPendentes;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import br.edu.ifpb.pautax.domain.entities.Processo;
import br.edu.ifpb.pautax.domain.entities.Professor;
import br.edu.ifpb.pautax.infrastructure.repositories.ProcessoRepository;
import br.edu.ifpb.pautax.infrastructure.repositories.ProfessorRepository;
import br.edu.ifpb.pautax.infrastructure.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ListarProcessosPendentesUseCase implements IListarProcessosPendentesUseCase {
    private final ProcessoRepository processoRepository;
    private final ProfessorRepository professorRepository;

    @Override
    @Transactional
    public ModelAndView execute(Pageable pageable, CustomUserDetails userDetails) {
        ModelAndView modelAndView = new ModelAndView("coordenador/listar-nao-distribuidos");

        Professor coordenador = professorRepository.findByUsuario(userDetails.getUsuario())
                .orElseThrow(() -> new RuntimeException("Coordenador não encontrado"));

        List<Professor> listaProfessores = professorRepository.findColegasDoCoordenador(coordenador.getId());       

        Page<Processo> processos = processoRepository.findByRelatorIsNull(pageable);

        modelAndView.addObject("processosPendentes", processos);
        modelAndView.addObject("listaProfessores", listaProfessores);

        return modelAndView;
    }
}
