package br.edu.ifpb.pautax.api.auth;

import br.edu.ifpb.pautax.domain.entities.Usuario;
import br.edu.ifpb.pautax.infrastructure.repositories.UsuarioRepository;
import br.edu.ifpb.pautax.infrastructure.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UsuarioRepository usuarioRepository;

    @GetMapping("/login")
    public String showLogin() {
        return "auth/login";
    }

    @GetMapping("/home")
    public String showHome() {
        return "home";
    }

    /**
     * Página inicial do aluno
     * <p>
     * Exibe a página inicial personalizada para o aluno autenticado.
     *
     * @param userDetails Detalhes do usuário autenticado. Obtido através da injeção do Spring Security com a anotação @AuthenticationPrincipal.
     * @return ModelAndView contendo a visão "home-aluno" e os dados do aluno.
     */
    @GetMapping("/home-aluno")
    public ModelAndView showHomeAluno(@AuthenticationPrincipal CustomUserDetails userDetails) {
//      Obtém o usuário autenticado a partir dos detalhes do usuário fornecidos pelo Spring Security
        Usuario aluno = userDetails.getUsuario();

//      Cria e retorna o ModelAndView com a visão "home-aluno" e os dados do aluno
        ModelAndView modelAndView = new ModelAndView("home-aluno");
        modelAndView.addObject("aluno", aluno);
        return modelAndView;
    }
}