package br.edu.ifpb.pautax.application.useCases.administrador.professores.deletar;

import br.edu.ifpb.pautax.infrastructure.repositories.ProfessorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeletarProfessorUseCase implements IDeletarProfessorUseCase{
    private final ProfessorRepository professorRepository;

    @Override
    public String execute(int id) {
        professorRepository.deleteById(id);
        return "redirect:/admin/professores";
    }
}
