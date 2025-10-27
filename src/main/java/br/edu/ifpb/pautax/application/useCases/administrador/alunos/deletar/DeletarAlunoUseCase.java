package br.edu.ifpb.pautax.application.useCases.administrador.alunos.deletar;

import br.edu.ifpb.pautax.infrastructure.repositories.AlunoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeletarAlunoUseCase implements IDeletarAlunoUseCase {
    private final AlunoRepository alunoRepository;

    @Override
    public String execute(Long id) {
        alunoRepository.deleteById(id);

        return "redirect:/admin/alunos";
    }
}
