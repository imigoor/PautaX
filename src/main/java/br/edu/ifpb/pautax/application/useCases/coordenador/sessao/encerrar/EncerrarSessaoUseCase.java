package br.edu.ifpb.pautax.application.useCases.coordenador.sessao.encerrar;

import br.edu.ifpb.pautax.domain.entities.Processo;
import br.edu.ifpb.pautax.domain.entities.Reuniao;
import br.edu.ifpb.pautax.domain.enums.StatusProcesso;
import br.edu.ifpb.pautax.domain.enums.StatusReuniao;
import br.edu.ifpb.pautax.infrastructure.repositories.ProcessoRepository;
import br.edu.ifpb.pautax.infrastructure.repositories.ReuniaoRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Service
@RequiredArgsConstructor
public class EncerrarSessaoUseCase implements IEncerrarSessaoUseCase {
    private final ReuniaoRepository reuniaoRepository;
    private final ProcessoRepository processoRepository;

    @Override
    @Transactional
    public String execute(Integer idSessao, RedirectAttributes redirectAttributes) {
        Reuniao reuniao = reuniaoRepository.findById(idSessao).orElseThrow(() -> new IllegalArgumentException("Sessão não encontrada"));

        reuniao.setStatus(StatusReuniao.ENCERRADA);

        reuniaoRepository.save(reuniao);

        List<Processo> processosDaSessao = reuniao.getProcessos();
        boolean houveProcessosDevolvidos = false;

        for (Processo processo : processosDaSessao) {
            // Se não foi julgado, reseta para ser distribuído novamente
            if (processo.getStatusProcesso() != StatusProcesso.JULGADO) {
                processo.setStatusProcesso(StatusProcesso.DISTRIBUIDO);
                processo.setReuniao(null);
                houveProcessosDevolvidos = true;
            }
        }

        processoRepository.saveAll(processosDaSessao);


        if (houveProcessosDevolvidos) {
            redirectAttributes.addFlashAttribute("sucesso", "Sessão encerrada. Processos não julgados voltaram para a fila.");
        } else {
            redirectAttributes.addFlashAttribute("sucesso", "Sessão encerrada com sucesso.");
        }

        return "redirect:/coordenador/listar-sessoes";
    }
}
