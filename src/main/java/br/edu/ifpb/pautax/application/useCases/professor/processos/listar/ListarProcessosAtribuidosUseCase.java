package br.edu.ifpb.pautax.application.useCases.professor.processos.listar;

import java.util.List;

import org.springframework.stereotype.Service;
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

    public ModelAndView execute(Usuario professorLogado) {
        ModelAndView modelAndView = new ModelAndView("professor/meus-processos");

        List<Processo> processos = processoRepository.findByRelator((Professor) professorLogado);

        modelAndView.addObject("processosAtribuidos", processos);

        return modelAndView;
    }
}
