package br.edu.ifpb.pautax.infrastructure.security;

import br.edu.ifpb.pautax.application.services.CustomUserDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 * Esta classe configura a segurança da aplicação usando Spring Security.
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final CustomUserDetailService userDetailService;

    /**
     * Configura o filtro de segurança para a aplicação.
     * @param http A instância de HttpSecurity usada para configurar a segurança web.
     * @return A cadeia de filtros de segurança configurada.
     * @throws Exception Se ocorrer um erro durante a configuração.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
//              Configura as regras de autorização para diferentes endpoints.
                .authorizeHttpRequests(auth -> auth
//                      Permite acesso público às páginas de login e aos recursos estáticos.
                        .requestMatchers("/login", "/css/*", "/js/*").permitAll()
                        .anyRequest().authenticated()
                )
//              Configura o formulário de login personalizado.
                .formLogin(form -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/login")
//                      Por padrão, o Spring Security usa "username" como o nome do parâmetro de nome de usuário
//                      Mas aqui estamos alterando para "login", devido a requisitos específicos do projeto.
//                      No formulário HTML, o campo de entrada para o nome de usuário deve ter o atributo name definido como "login".
                        .usernameParameter("login")
//                      Em caso de sucesso no login, o manipulador personalizado é chamado para redirecionar o usuário
                        .successHandler(customAuthenticationSuccessHandler())
                        .permitAll()
                )
//              Ainda não implementado: Configura o logout.
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                        .permitAll()
                )
//              Nessa parte, configuramos o que acontece quando um usuário não autenticado tenta acessar um recurso protegido.
                .exceptionHandling(ex -> ex
                        .authenticationEntryPoint((request, response, authException) -> {
//                          Usuário é redirecionado para a página de login se não estiver autenticado.
                            response.sendRedirect("/login");
                        })
                );



        return http.build();
    }

    /**
     * Configura o codificador de senhas usando BCrypt.
     * @return A instância do PasswordEncoder.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Configura o provedor de autenticação DAO.
     * @return A instância do DaoAuthenticationProvider.
     */
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    /**
     * Configura o manipulador de sucesso de autenticação personalizado.
     * @return A instância do AuthenticationSuccessHandler.
     */
    @Bean
    public AuthenticationSuccessHandler customAuthenticationSuccessHandler() {
        return new CustomAuthenticationSuccessHandler();
    }
}