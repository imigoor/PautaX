package br.edu.ifpb.pautax.application.useCases.aluno.processo.listar;

import br.edu.ifpb.pautax.domain.entities.Aluno;
import br.edu.ifpb.pautax.domain.entities.Processo;
import br.edu.ifpb.pautax.domain.entities.Usuario;
import br.edu.ifpb.pautax.infrastructure.repositories.AlunoRepository;
import br.edu.ifpb.pautax.infrastructure.repositories.AssuntoRepository;
import br.edu.ifpb.pautax.infrastructure.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

@Service
@RequiredArgsConstructor
public class ListarProcessoAlunoUseCase implements IListarProcessoAlunoUseCase{
    private final AssuntoRepository assuntoRepository;

    @Override
    public ModelAndView execute(CustomUserDetails userDetails) {
        ModelAndView modelAndView = new ModelAndView("aluno/gerenciar-processo");

        modelAndView.addObject("listaAssuntos", assuntoRepository.findAll());
        modelAndView.addObject("processo", new Processo());

        return modelAndView;
    }
}
