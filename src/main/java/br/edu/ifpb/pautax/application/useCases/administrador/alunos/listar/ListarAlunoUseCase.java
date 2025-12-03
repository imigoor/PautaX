package br.edu.ifpb.pautax.application.useCases.administrador.alunos.listar;

import br.edu.ifpb.pautax.domain.entities.Aluno;
import br.edu.ifpb.pautax.infrastructure.repositories.AlunoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

@Service
@RequiredArgsConstructor
public class ListarAlunoUseCase implements IListarAlunoUseCase{
    private final AlunoRepository alunoRepository;

    public ModelAndView execute() {
        ModelAndView modelAndView = new ModelAndView("administrador/gerenciar-alunos");
        modelAndView.addObject("aluno", new Aluno());
        modelAndView.addObject("listaDeAlunos", alunoRepository.findAll());
        return modelAndView;
    }
}
