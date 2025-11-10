package br.edu.ifpb.pautax.api.professor;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.edu.ifpb.pautax.application.useCases.professor.processos.listar.IListarProcessosAtribuidosUseCase;
import br.edu.ifpb.pautax.domain.entities.Usuario;
import br.edu.ifpb.pautax.infrastructure.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/professor")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ROLE_PROFESSOR')")
public class ProfessorController {

    private final IListarProcessosAtribuidosUseCase listarProcessosAtribuidosUseCase;

    @GetMapping("/home-professor")
    public ModelAndView mostrarHomeProfessor() {
        return new ModelAndView("professor/home-professor");
    }

    @GetMapping("/meus-processos")
    public ModelAndView mostrarMeusProcessos(@AuthenticationPrincipal CustomUserDetails userDetails) {

        Usuario professorLogado = userDetails.getUsuario();

        return listarProcessosAtribuidosUseCase.execute(professorLogado);
    }
}
