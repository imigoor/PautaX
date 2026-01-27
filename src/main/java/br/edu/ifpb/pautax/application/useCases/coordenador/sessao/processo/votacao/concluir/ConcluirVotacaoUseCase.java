package br.edu.ifpb.pautax.application.useCases.coordenador.sessao.processo.votacao.concluir;

import br.edu.ifpb.pautax.domain.entities.Processo;
import br.edu.ifpb.pautax.domain.enums.StatusProcesso;
import br.edu.ifpb.pautax.domain.enums.TipoDecisao;
import br.edu.ifpb.pautax.domain.enums.TipoVoto;
import br.edu.ifpb.pautax.infrastructure.repositories.ProcessoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ConcluirVotacaoUseCase implements IConcluirVotacaoUseCase{
    private final ProcessoRepository processoRepository;

    @Override
    @Transactional
    public String execute(Integer idProcesso) {
        Processo processo = processoRepository.findById(idProcesso)
                .orElseThrow(() -> new RuntimeException("Processo não encontrado"));

        if (processo.getVotos() == null || processo.getVotos().isEmpty()) {
            throw new RuntimeException("Não é possível concluir votação sem votos");
        }

        if (processo.getStatusProcesso() == StatusProcesso.JULGADO) {
            throw new IllegalStateException("Processo já foi concluído");
        }

        long votosComRelator = processo.getVotos().stream()
                .filter(v -> !v.isAusente())
                .filter(v -> v.getVoto() == TipoVoto.COM_RELATOR)
                .count();

        long votosDivergentes = processo.getVotos().stream()
                .filter(v -> !v.isAusente())
                .filter(v -> v.getVoto() == TipoVoto.DIVERGENTE)
                .count();

        TipoDecisao decisaoFinal;

        if (votosDivergentes >= votosComRelator) {
            decisaoFinal = inverterDecisao(processo.getDecisaoRelator());
        } else {
            decisaoFinal = processo.getDecisaoRelator();
        }

        processo.setDecisaoFinal(decisaoFinal);
        processo.setStatusProcesso(StatusProcesso.JULGADO);

        processoRepository.save(processo);

        return "redirect:/coordenador/conduzir-sessao/" + processo.getReuniao().getId();
    }

    private TipoDecisao inverterDecisao(TipoDecisao decisao) {
        return decisao == TipoDecisao.DEFERIMENTO
                ? TipoDecisao.INDEFERIMENTO
                : TipoDecisao.DEFERIMENTO;
    }
}
