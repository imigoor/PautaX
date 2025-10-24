package br.edu.ifpb.pautax.application.services;

import br.edu.ifpb.pautax.domain.entities.Usuario;
import br.edu.ifpb.pautax.infrastructure.repositories.UsuarioRepository;
import br.edu.ifpb.pautax.infrastructure.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * CustomUserDetailService é uma implementação personalizada de UserDetailsService
 * que carrega os detalhes do usuário a partir do repositório de usuários.
 */
@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {
    private final UsuarioRepository usuarioRepository;

    /**
     * Carrega os detalhes do usuário com base no login fornecido.
     * @param login O login do usuário.
     * @return CustomUserDetails contendo as informações do usuário.
     * @throws UsernameNotFoundException se o usuário não for encontrado.
     */
    @Override
    public UserDetails loadUserByUsername(String login) {
        Usuario usuario = usuarioRepository.findByLogin(login)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));

        return new CustomUserDetails(usuario);
    }
}