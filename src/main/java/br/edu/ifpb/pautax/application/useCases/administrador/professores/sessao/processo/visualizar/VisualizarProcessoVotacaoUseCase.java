package br.edu.ifpb.pautax.application.useCases.administrador.professores.sessao.processo.visualizar;

import br.edu.ifpb.pautax.application.dto.ProcessoVotacaoDTO;
import br.edu.ifpb.pautax.domain.entities.Processo;
import br.edu.ifpb.pautax.infrastructure.repositories.ProcessoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

@Service
@RequiredArgsConstructor
public class VisualizarProcessoVotacaoUseCase implements IVisualizarProcessoVotacaoUseCase {
    private final ProcessoRepository processoRepository;

    @Override
    @Transactional(readOnly = true)
    public ModelAndView execute(Integer processoId) {

        Processo processo = processoRepository.findById(processoId)
                .orElseThrow(() -> new RuntimeException("Processo não encontrado"));

        ProcessoVotacaoDTO dto = new ProcessoVotacaoDTO(
                processo.getId(),
                processo.getNumero(),
                processo.getAssunto().getNome(),
                processo.getRelator().getId(),
                processo.getRelator().getUsuario().getNome(),
                processo.getDecisaoRelator(),
                processo.getReuniao().getId()
        );

        ModelAndView mv = new ModelAndView("professor/votar-processo");
        mv.addObject("processo", dto);

        return mv;
    }
}
