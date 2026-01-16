package br.edu.ifpb.pautax.application.useCases.administrador.sessao.deletar;

import br.edu.ifpb.pautax.infrastructure.repositories.ProcessoRepository;
import br.edu.ifpb.pautax.infrastructure.repositories.ReuniaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import br.edu.ifpb.pautax.domain.entities.Processo;
import br.edu.ifpb.pautax.domain.entities.Reuniao;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DeletarSessaoUseCase implements IDeletarSessaoUseCase {

    private final ReuniaoRepository reuniaoRepository;
    private final ProcessoRepository processoRepository;

    @Override
    @Transactional
    public String execute(Integer idReuniao) {
        Reuniao reuniao = reuniaoRepository.findById(idReuniao)
                .orElseThrow(() -> new IllegalArgumentException("Reunião não encontrada"));

        List<Processo> processosDaPauta = reuniao.getProcessos();
        if (processosDaPauta != null) {
            for (Processo processo : processosDaPauta) {
                processo.setReuniao(null);
                processoRepository.save(processo);
            }
        }

        reuniaoRepository.delete(reuniao);
        return "redirect:/coordenador/listar-sessoes";
    }
}
