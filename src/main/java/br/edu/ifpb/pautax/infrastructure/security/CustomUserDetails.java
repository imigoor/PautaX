package br.edu.ifpb.pautax.infrastructure.security;

import br.edu.ifpb.pautax.domain.entities.Usuario;
import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

/**
 * CustomUserDetails é uma implementação personalizada de UserDetails que encapsula
 * as informações do usuário da aplicação.
 */
@Getter
public class CustomUserDetails extends User {
    private final Usuario usuario;

    /**
     * Construtor que inicializa o CustomUserDetails com as informações do usuário.
     * @param usuario O objeto Usuario contendo os detalhes do usuário.
     */
    public CustomUserDetails(Usuario usuario) {
        super(usuario.getLogin(), usuario.getSenha(),
                usuario.getRole().stream().map(SimpleGrantedAuthority::new).toList());
        this.usuario = usuario;
    }
}