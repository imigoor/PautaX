package br.edu.ifpb.pautax.application.useCases.coordenador.sessao.mostrar;

import br.edu.ifpb.pautax.domain.entities.Reuniao;
import br.edu.ifpb.pautax.infrastructure.repositories.ReuniaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MostrarSessaoUseCase implements IMostrarSessaoUseCase {
    private final ReuniaoRepository reuniaoRepository;

    @Override
    @Transactional
    public ModelAndView execute(Integer idReuniao) {

        ModelAndView mv = new ModelAndView("coordenador/conduzir-sessao");

        Reuniao sessao = reuniaoRepository.findById(idReuniao)
                .orElseThrow(() -> new IllegalArgumentException("Sessão não encontrada"));

        sessao.getProcessos().size();

        mv.addObject("sessao", sessao);
        mv.addObject("processoSelecionado", null);
        mv.addObject("votos", List.of());

        return mv;
    }
}
