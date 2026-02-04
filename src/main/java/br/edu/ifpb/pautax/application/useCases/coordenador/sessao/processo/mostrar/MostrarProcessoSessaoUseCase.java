package br.edu.ifpb.pautax.application.useCases.coordenador.sessao.processo.mostrar;

import br.edu.ifpb.pautax.application.dto.VotoProfessorDTO;
import br.edu.ifpb.pautax.domain.entities.Processo;
import br.edu.ifpb.pautax.domain.entities.Reuniao;
import br.edu.ifpb.pautax.domain.entities.Voto;
import br.edu.ifpb.pautax.infrastructure.repositories.ProcessoRepository;
import br.edu.ifpb.pautax.infrastructure.repositories.ReuniaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MostrarProcessoSessaoUseCase implements IMostrarProcessoSessaoUseCase {

    private final ReuniaoRepository reuniaoRepository;
    private final ProcessoRepository processoRepository;

    @Override
    @Transactional
    public ModelAndView execute(Integer idSessao, Integer idProcesso) {

        ModelAndView mv = new ModelAndView("coordenador/conduzir-sessao");

        Reuniao sessao = reuniaoRepository.findById(idSessao)
                .orElseThrow(() -> new IllegalArgumentException("Sessão não encontrada"));

        sessao.getProcessos().size();

        Processo processo = processoRepository.findById(idProcesso)
                .orElseThrow(() -> new IllegalArgumentException("Processo não encontrado"));

        if (!(processo.getReuniao().getId() ==idSessao)) {
            throw new IllegalStateException("Processo não pertence a esta sessão");
        }

        sessao.getColegiado().getMembros().size();
        processo.getVotos().size();

        int idRelator = processo.getRelator().getId();

        Map<Integer, Voto> votosPorProfessor = processo.getVotos()
                .stream()
                .collect(Collectors.toMap(
                        v -> v.getProfessor().getId(),
                        v -> v,
                        (votoExistente, votoNovo) -> votoExistente // Se houver duplicata, mantém o primeiro
                ));

        List<VotoProfessorDTO> votosDTO = sessao.getColegiado()
                .getMembros()
                .stream()
                .filter(prof -> !(prof.getId() == idRelator))
                .map(prof -> {
                    Voto voto = votosPorProfessor.get(prof.getId());
                    return new VotoProfessorDTO(
                            prof.getUsuario().getNome(),
                            voto != null ? voto.getVoto() : null
                    );
                })
                .collect(Collectors.toList());

        mv.addObject("sessao", sessao);
        mv.addObject("processoSelecionado", processo);
        mv.addObject("votos", votosDTO);

        return mv;
    }
}