package br.edu.ifpb.pautax.application.useCases.administrador.professores.sessao.processo.votar;

import br.edu.ifpb.pautax.domain.entities.Processo;
import br.edu.ifpb.pautax.domain.entities.Professor;
import br.edu.ifpb.pautax.domain.entities.Voto;
import br.edu.ifpb.pautax.domain.enums.TipoVoto;
import br.edu.ifpb.pautax.infrastructure.repositories.ProcessoRepository;
import br.edu.ifpb.pautax.infrastructure.repositories.ProfessorRepository;
import br.edu.ifpb.pautax.infrastructure.repositories.VotoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class VotarProcessoUseCase implements IVotarProcessoUseCase {
    private final ProcessoRepository processoRepository;
    private final ProfessorRepository professorRepository;
    private final VotoRepository votoRepository;

    @Transactional
    @Override
    public String executar(int processoId,
                           int professorId,
                           TipoVoto tipoVoto,
                           String justificativa) {

        Processo processo = processoRepository.findById(processoId)
                .orElseThrow(() -> new RuntimeException("Processo não encontrado"));

        Professor professor = (Professor) professorRepository.findByUsuarioId(professorId)
                .orElseThrow(() -> new RuntimeException("Professor não encontrado"));

        boolean jaVotou = processo.getVotos().stream()
                .anyMatch(v -> v.getProfessor().getId() == professorId);

        if (jaVotou) {
            throw new RuntimeException("Professor já votou neste processo");
        }

        Voto voto = new Voto();
        voto.setProfessor(professor);
        voto.setProcesso(processo);
        voto.setVoto(tipoVoto);
        voto.setJustificativa(justificativa);
        voto.setAusente(false);

        votoRepository.save(voto);

        return "redirect:/professor/visualizar-sessao/" + processo.getReuniao().getId();
    }
}
