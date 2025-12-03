package br.edu.ifpb.pautax.application.useCases.administrador.professores.deletar;

import br.edu.ifpb.pautax.infrastructure.repositories.ColegiadoRepository;
import br.edu.ifpb.pautax.infrastructure.repositories.ProcessoRepository;
import br.edu.ifpb.pautax.infrastructure.repositories.ProfessorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DeletarProfessorUseCase implements IDeletarProfessorUseCase{
    private final ProfessorRepository professorRepository;
    private final ColegiadoRepository colegiadoRepository;
    private final ProcessoRepository processoRepository;

    @Override
    @Transactional
    public String execute(int id) {
        var professor = professorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Professor não encontrado"));

        var colegiados = colegiadoRepository.findAllByMembros_Id(id);

        colegiados.forEach(colegiado -> {
            colegiado.getMembros().remove(professor);
            colegiadoRepository.save(colegiado);
        });

        var processos = processoRepository.findByRelator(professor);

        processos.forEach(processo -> {
            processo.setRelator(null);
            processoRepository.save(processo);
        });

        professorRepository.deleteById(id);
        return "redirect:/admin/professores";
    }
}
