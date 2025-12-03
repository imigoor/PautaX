package br.edu.ifpb.pautax.application.useCases.professor.processos.listar;

import java.util.List;

import br.edu.ifpb.pautax.infrastructure.repositories.ProfessorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import br.edu.ifpb.pautax.domain.entities.Processo;
import br.edu.ifpb.pautax.domain.entities.Professor;
import br.edu.ifpb.pautax.domain.entities.Usuario;
import br.edu.ifpb.pautax.infrastructure.repositories.ProcessoRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ListarProcessosAtribuidosUseCase implements IListarProcessosAtribuidosUseCase {

    private final ProcessoRepository processoRepository;
    private final ProfessorRepository professorRepository;

    @Override
    @Transactional
    public ModelAndView execute(Usuario professorLogado) {
        Professor professor = professorRepository.findByUsuario(professorLogado)
                .orElseThrow(() -> new RuntimeException("Professor não encontrado."));;

        ModelAndView modelAndView = new ModelAndView("professor/meus-processos");

        List<Processo> processos = processoRepository.findByRelator(professor);

        modelAndView.addObject("processosAtribuidos", processos);

        return modelAndView;
    }
}
