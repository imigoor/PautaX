package br.edu.ifpb.pautax.application.useCases.professor.processos.parecer;

import java.time.LocalDate;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.edu.ifpb.pautax.domain.entities.Processo;
import br.edu.ifpb.pautax.domain.enums.StatusProcesso;
import br.edu.ifpb.pautax.domain.enums.TipoDecisao;
import br.edu.ifpb.pautax.infrastructure.repositories.ProcessoRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmitirParecerUseCase implements IEmitirParecerUseCase {
    private final ProcessoRepository processoRepository;

    @Override
    @Transactional
    public String execute(Integer idProcesso, TipoDecisao decisao, String textoParecer) {
        Processo processo = processoRepository.findById(idProcesso)
                .orElseThrow(() -> new RuntimeException("Processo não encontrado"));

        processo.setDecisaoRelator(decisao);
                
        if (textoParecer != null) {
            processo.setParecer(textoParecer.getBytes());
        }

        processo.setDataParecer(LocalDate.now());
                
        processo.setStatusProcesso(StatusProcesso.DISPONIVEL);

        processoRepository.save(processo);

        return "redirect:/professor/meus-processos";
    }
}
