package br.edu.ifpb.pautax.application.useCases.administrador.professores.sessao.mostrar;

import br.edu.ifpb.pautax.application.dto.ProcessoResumoDTO;
import br.edu.ifpb.pautax.application.dto.SessaoDTO;
import br.edu.ifpb.pautax.domain.entities.Reuniao;
import br.edu.ifpb.pautax.infrastructure.repositories.ReuniaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import java.time.ZoneId;

@Service
@RequiredArgsConstructor
public class VisualizarSessaoUseCase implements IvisualizarSessaoUseCase {

    private final ReuniaoRepository reuniaoRepository;

    @Transactional(readOnly = true)
    public ModelAndView execute(Integer sessaoId) {

        Reuniao reuniao = reuniaoRepository.findById(sessaoId)
                .orElseThrow(() -> new RuntimeException("Sessão não encontrada"));

        SessaoDTO sessaoDTO = new SessaoDTO(
                reuniao.getId(),
                reuniao.getDataReuniao().toInstant()
                        .atZone(ZoneId.systemDefault())
                        .toLocalDateTime(),
                reuniao.getStatus(),
                reuniao.getColegiado().getDescricao(),
                reuniao.getProcessos().stream()
                        .map(p -> new ProcessoResumoDTO(
                                p.getId(),
                                p.getNumero(),
                                p.getAssunto().getNome(),
                                p.getStatusProcesso(),
                                p.getRelator().getId(),
                                p.getRelator().getUsuario().getNome()
                        ))
                        .toList()
        );

        ModelAndView mv = new ModelAndView("professor/visualizar-sessao");
        mv.addObject("sessao", sessaoDTO);

        return mv;
    }

}