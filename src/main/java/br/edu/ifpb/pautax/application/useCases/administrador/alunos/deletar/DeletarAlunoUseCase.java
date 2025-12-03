package br.edu.ifpb.pautax.application.useCases.administrador.alunos.deletar;

import br.edu.ifpb.pautax.domain.entities.Aluno;
import br.edu.ifpb.pautax.domain.entities.Processo;
import br.edu.ifpb.pautax.infrastructure.repositories.AlunoRepository;
import br.edu.ifpb.pautax.infrastructure.repositories.ProcessoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DeletarAlunoUseCase implements IDeletarAlunoUseCase {
    private final AlunoRepository alunoRepository;
    private final ProcessoRepository processoRepository;

    @Override
    @Transactional
    public String execute(int id, RedirectAttributes attributes) {

        Aluno aluno = alunoRepository.findById(id).get();

        List<Processo> processos = processoRepository.findAllByInteressado(aluno);

        for (Processo processo : processos) {
            if (processo.getInteressado().equals(aluno)) {
                attributes.addFlashAttribute("erro", "Aluno não pode ser deletado pois tem um processo vinculado à ele.");
                return "redirect:/admin/alunos";
            }
        }

        alunoRepository.deleteById(id);
        return "redirect:/admin/alunos";
    }
}
