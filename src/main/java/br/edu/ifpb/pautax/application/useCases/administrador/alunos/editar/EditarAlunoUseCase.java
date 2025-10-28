package br.edu.ifpb.pautax.application.useCases.administrador.alunos.editar;

import br.edu.ifpb.pautax.domain.entities.Aluno;
import br.edu.ifpb.pautax.infrastructure.repositories.AlunoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

@Service
@RequiredArgsConstructor
public class EditarAlunoUseCase implements IEditarAlunoUseCase{
    private final AlunoRepository alunoRepository;

    @Override
    public ModelAndView execute(int id) {
        ModelAndView modelAndView = new ModelAndView("administrador/gerenciar-alunos");
        modelAndView.addObject("aluno", alunoRepository.findById(id).orElse(new Aluno()));
        modelAndView.addObject("listaDeAlunos", alunoRepository.findAll());
        return modelAndView;
    }
}
