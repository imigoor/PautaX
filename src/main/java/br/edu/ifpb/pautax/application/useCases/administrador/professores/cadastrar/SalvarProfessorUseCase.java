package br.edu.ifpb.pautax.application.useCases.administrador.professores.cadastrar;

import br.edu.ifpb.pautax.domain.entities.Professor;
import br.edu.ifpb.pautax.domain.entities.Usuario;
import br.edu.ifpb.pautax.infrastructure.repositories.ProfessorRepository;
import br.edu.ifpb.pautax.infrastructure.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class SalvarProfessorUseCase implements ISalvarProfessorUseCase {
    private final ProfessorRepository professorRepository;
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder encoder;

    @Override
    public String execute(Professor professor) {
        Usuario usuario = professor.getUsuario(); // Objeto vindo do formulário

//        if (usuario.getId() > 0) {
//            // É EDIÇÃO: Buscamos o usuário existente para obter o ID e a senha antiga.
//            Usuario usuarioExistente = usuarioRepository.findById(usuario.getId())
//                    .orElseThrow(() -> new RuntimeException("Usuário a ser editado não encontrado."));
//            usuario.setId(usuarioExistente.getId());
//        } else {
        usuario.setSenha(encoder.encode(usuario.getSenha()));
        //}

        if (professor.isCoordenador()) {
            usuario.setRole(Set.of("ROLE_COORDENADOR"));
        } else {
            usuario.setRole(Set.of("ROLE_PROFESSOR"));
        }

        usuarioRepository.save(usuario);

        professorRepository.save(professor);

        return "redirect:/admin/professores";
    }
}
