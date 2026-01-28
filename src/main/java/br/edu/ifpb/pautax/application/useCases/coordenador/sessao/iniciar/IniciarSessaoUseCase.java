package br.edu.ifpb.pautax.application.useCases.coordenador.sessao.iniciar;

import br.edu.ifpb.pautax.domain.entities.Processo;
import br.edu.ifpb.pautax.domain.entities.Reuniao;
import br.edu.ifpb.pautax.domain.enums.StatusProcesso;
import br.edu.ifpb.pautax.domain.enums.StatusReuniao;
import br.edu.ifpb.pautax.infrastructure.repositories.ProcessoRepository;
import br.edu.ifpb.pautax.infrastructure.repositories.ReuniaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IniciarSessaoUseCase implements IIniciarSessaoUseCase {
    private final ReuniaoRepository reuniaoRepository;
    private final ProcessoRepository processoRepository;

    @Override
    @Transactional
    public String execute(Integer idReuniao) {
        Reuniao reuniao = reuniaoRepository.findById(idReuniao).orElseThrow(() -> new IllegalArgumentException("Reunião não encontrada."));

        if (reuniaoRepository.existsByStatusAndIdNot(StatusReuniao.INICIADA, idReuniao)) {
            throw new IllegalArgumentException("Já existe uma sessão em andamento.");
        }

        if (reuniao.getStatus().equals(StatusReuniao.ENCERRADA)) {
            throw new IllegalArgumentException("A sessão já está encerrada.");
        }

        if (reuniao.getStatus().equals(StatusReuniao.INICIADA)) {
            return "redirect:/coordenador/conduzir-sessao/"  + idReuniao;
        }

        if (reuniao.getStatus() != StatusReuniao.PROGRAMADA) {
            throw new IllegalStateException("A sessão não pode ser iniciada.");
        }

        reuniao.setStatus(StatusReuniao.INICIADA);
        reuniaoRepository.save(reuniao);

        List<Processo> processos = processoRepository.findAllByReuniao_Id(idReuniao);

        for (Processo processo : processos) {
            processo.setStatusProcesso(StatusProcesso.EM_JULGAMENTO);
        }

        processoRepository.saveAll(processos);

        return "redirect:/coordenador/conduzir-sessao/" + idReuniao;
    }
}
