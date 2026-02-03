package br.edu.ifpb.pautax.application.useCases.coordenador.sessao.cadastrar;

import br.edu.ifpb.pautax.domain.entities.Reuniao;
import br.edu.ifpb.pautax.domain.enums.StatusProcesso;
import br.edu.ifpb.pautax.infrastructure.repositories.ColegiadoRepository;
import br.edu.ifpb.pautax.infrastructure.repositories.ReuniaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import br.edu.ifpb.pautax.domain.entities.Colegiado;
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
    private final ColegiadoRepository colegiadoRepository;

    @Override
    @Transactional
    public String execute(CriarSessaoFormDTO form) {
        Colegiado colegiado = colegiadoRepository.findById(form.getColegiadoId())
                .orElseThrow(() -> new IllegalArgumentException("Colegiado inválido"));
        
        List<Processo> processosDaPauta =
                processoRepository.findAllById(form.getProcessosIds());

        for (Processo processo : processosDaPauta) {
            
            // Verifica se existe algum membro no colegiado com o mesmo ID do relator do processo
            boolean relatorEhMembro = colegiado.getMembros().stream()
                    .anyMatch(membro -> membro.getId() == processo.getRelator().getId());

            if (!relatorEhMembro) {
                throw new IllegalArgumentException(
                    "O processo nº " + processo.getNumero() + 
                    " não pode ser pautado. O relator (" + processo.getRelator().getUsuario().getNome() + 
                    ") não faz parte do colegiado selecionado."
                );
            }
        }

        Reuniao sessao = new Reuniao();
        sessao.setDataReuniao(form.getDataReuniao());
        sessao.setStatus(StatusReuniao.PROGRAMADA);
        sessao.setColegiado(colegiado);

        Reuniao sessaoSalva = reuniaoRepository.save(sessao);
                
        for (Processo processo : processosDaPauta) {
            processo.setReuniao(sessaoSalva);
            processo.setStatusProcesso(StatusProcesso.EM_PAUTA);
        }

        processoRepository.saveAll(processosDaPauta);

        return "redirect:/coordenador/listar-sessoes";
    }
}
