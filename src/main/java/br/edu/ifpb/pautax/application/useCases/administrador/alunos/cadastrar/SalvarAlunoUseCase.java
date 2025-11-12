package br.edu.ifpb.pautax.application.useCases.administrador.alunos.cadastrar;

import br.edu.ifpb.pautax.domain.entities.Aluno;
import br.edu.ifpb.pautax.infrastructure.repositories.AlunoRepository;
import br.edu.ifpb.pautax.infrastructure.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class SalvarAlunoUseCase implements ISalvarAlunoUseCase{
    private final AlunoRepository alunoRepository;
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder encoder;

    @Override
    public String execute(Aluno aluno) {
        Aluno alunoSalvo = alunoRepository.findById(aluno.getId()).orElse(null);

        if (alunoSalvo == null){
            aluno.getUsuario().setRole(Set.of("ROLE_ALUNO"));
        }
        aluno.getUsuario().setSenha(encoder.encode(aluno.getUsuario().getSenha()));
        usuarioRepository.save(aluno.getUsuario());
        alunoRepository.save(aluno);

        return "redirect:/admin/alunos";
    }
}
