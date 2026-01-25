package br.edu.ifpb.pautax.application.useCases.coordenador.sessao.cadastrar;

import br.edu.ifpb.pautax.domain.entities.Reuniao;
import br.edu.ifpb.pautax.infrastructure.repositories.ReuniaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import br.edu.ifpb.pautax.domain.entities.Processo;
import br.edu.ifpb.pautax.domain.enums.StatusReuniao;
import br.edu.ifpb.pautax.infrastructure.repositories.ProcessoRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CriarSessaoUseCase implements ICriarSessaoUseCase {

    private final ReuniaoRepository reuniaoRepository;
    private final ProcessoRepository processoRepository;

    @Override
    @Transactional
    public String execute(Reuniao sessao) {
        sessao.setStatus(StatusReuniao.PROGRAMADA);

        Reuniao sessaoSalva = reuniaoRepository.save(sessao);
        List<Integer> idsDaPauta = sessao.getProcessosIds();

        if (idsDaPauta != null && !idsDaPauta.isEmpty()) {
            List<Processo> processosDaPauta = processoRepository.findAllById(idsDaPauta);

            for (Processo processo : processosDaPauta) {
                processo.setReuniao(sessaoSalva);
                processoRepository.save(processo);
            }
        }

        return "redirect:/coordenador/listar-sessoes";
    }
}
