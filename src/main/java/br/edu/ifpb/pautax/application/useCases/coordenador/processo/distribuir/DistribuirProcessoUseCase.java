package br.edu.ifpb.pautax.application.useCases.coordenador.processo.distribuir;

import br.edu.ifpb.pautax.domain.entities.Processo;
import br.edu.ifpb.pautax.domain.entities.Professor;
import br.edu.ifpb.pautax.infrastructure.repositories.ProcessoRepository;
import br.edu.ifpb.pautax.infrastructure.repositories.ProfessorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class DistribuirProcessoUseCase implements IDistribuirProcessoUseCase {
    private final ProcessoRepository processoRepository;
    private final ProfessorRepository professorRepository;

    @Override
    public String execute(Integer idProcesso, Integer idRelator) {
        Processo processo = processoRepository.findById(idProcesso).get();
        Professor relator = professorRepository.findById(idRelator).get();

        processo.setRelator(relator);
        processo.setDataDistribuicao(LocalDate.now());

        processoRepository.save(processo);

        return "redirect:/coordenador/listar-pendentes";
    }
}