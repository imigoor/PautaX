package br.edu.ifpb.pautax.application.useCases.coordenador.sessao.iniciar;

import br.edu.ifpb.pautax.domain.entities.Reuniao;
import br.edu.ifpb.pautax.domain.enums.StatusReuniao;
import br.edu.ifpb.pautax.infrastructure.repositories.ReuniaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class IniciarSessaoUseCase implements IIniciarSessaoUseCase {
    private final ReuniaoRepository reuniaoRepository;

    @Override
    @Transactional
    public String execute(Integer idReuniao) {
        Reuniao reuniao = reuniaoRepository.findById(idReuniao).orElseThrow(() -> new IllegalArgumentException("Reunião não encontrada."));

        if (reuniaoRepository.existsByStatusAndIdNot(StatusReuniao.INICIADA, idReuniao)) {
            throw new IllegalArgumentException("Já existe uma sessão em andamento.");
        }

        if (reuniao.getStatus().equals(StatusReuniao.INICIADA)) {
            return "redirect:/coordenador/conduzir-sessao/"  + idReuniao;
        }

        if (reuniao.getStatus() != StatusReuniao.PROGRAMADA) {
            throw new IllegalStateException("A sessão não pode ser iniciada.");
        }

        reuniao.setStatus(StatusReuniao.INICIADA);
        reuniaoRepository.save(reuniao);

        return "redirect:/coordenador/conduzir-sessao/" + idReuniao;
    }
}
