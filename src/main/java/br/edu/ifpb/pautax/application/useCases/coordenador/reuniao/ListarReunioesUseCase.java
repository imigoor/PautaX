package br.edu.ifpb.pautax.application.useCases.coordenador.reuniao;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.edu.ifpb.pautax.domain.entities.Reuniao;
import br.edu.ifpb.pautax.domain.enums.StatusReuniao;
import br.edu.ifpb.pautax.infrastructure.repositories.ReuniaoRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ListarReunioesUseCase implements IListarReunioesUseCase {
    private final ReuniaoRepository reuniaoRepository;    

    @Override    
    @Transactional(readOnly = true)
    public List<Reuniao> execute(StatusReuniao status) {
        List<Reuniao> reunioes;

        if (status == null) {
            reunioes = reuniaoRepository.findByStatus(StatusReuniao.PROGRAMADA);
        } else {
            reunioes = reuniaoRepository.findByStatus(status);
        }

        reunioes.forEach(r -> r.getProcessos().size());

        return reunioes;
    }
}
