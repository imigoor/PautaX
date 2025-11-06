package br.edu.ifpb.pautax.application.useCases.aluno.home;

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
public class MostrarHomeUseCase implements IMostrarHomeUseCase{
    private final AssuntoRepository assuntoRepository;
    private final AlunoRepository alunoRepository;

    @Override
    public ModelAndView execute(CustomUserDetails userDetails) {
        Usuario usuario = userDetails.getUsuario();

        Aluno aluno = alunoRepository.findByUsuario(usuario)
                        .orElseThrow(() -> new RuntimeException("Aluno não encontrado..."));;

        // Cria e retorna o ModelAndView com a visão "home-aluno" e os dados necessários
        ModelAndView modelAndView = new ModelAndView("aluno/home-aluno");

        modelAndView.addObject("aluno", aluno);
        modelAndView.addObject("listaAssuntos", assuntoRepository.findAll());
        modelAndView.addObject("processo", new Processo());

        return modelAndView;
    }
}
