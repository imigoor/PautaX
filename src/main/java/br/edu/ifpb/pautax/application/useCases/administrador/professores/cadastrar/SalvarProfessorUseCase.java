package br.edu.ifpb.pautax.application.useCases.administrador.professores.cadastrar;

import br.edu.ifpb.pautax.domain.entities.Professor;
import br.edu.ifpb.pautax.infrastructure.repositories.ProfessorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class SalvarProfessorUseCase implements ISalvarProfessorUseCase {
    private final ProfessorRepository professorRepository;
    private final PasswordEncoder encoder;

    @Override
    public String execute(Professor professor) {
        professor.setSenha(encoder.encode(professor.getSenha()));
        if (professor.isCoordenador()) {
            professor.setRole(Set.of("ROLE_COORDENADOR"));
        } else {
            professor.setRole(Set.of("ROLE_PROFESSOR"));
        }
        professorRepository.save(professor);
        return "redirect:/admin/professores";
    }
}
