package br.edu.ifpb.pautax.infrastructure.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collection;

/**
 * Esta classe lida com o redirecionamento após o login bem-sucedido,
 * direcionando os usuários para diferentes páginas com base em seus papéis.
 */
@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    /**
     * Método chamado após o login bem-sucedido.
     *
     * @param request A requisição HTTP
     * @param response A resposta HTTP
     * @param authentication A autenticação do usuário
     * @throws IOException Se ocorrer um erro de E/S durante o redirecionamento
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException {

//      Obtém as autoridades do usuário autenticado
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        String redirectURL = "/home"; // Endereço padrão

//      Verifica o papel do usuário e define o redirecionamento apropriado
        if (authorities.stream().anyMatch(a -> a.getAuthority().equals("ROLE_ALUNO"))) {
            redirectURL = "/aluno/home-aluno";
        } else if (authorities.stream().anyMatch(a -> a.getAuthority().equals("ROLE_COORDENADOR"))) {
            redirectURL = "/coordenador/home-coordenador";
        } else if (authorities.stream().anyMatch(a -> a.getAuthority().equals("ROLE_PROFESSOR"))) {
            redirectURL = "/professor/home-professor";
        } else if (authorities.stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN")))
        {
            redirectURL = "/admin/home-admin";
        }

        response.sendRedirect(redirectURL);
    }
}