package br.edu.ifpb.pautax.domain.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.Set;

@Data
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "O nome é obrigatório.")
    @Size(min = 3, max = 50, message = "O nome deve ter entre 3 e 50 caracteres.")
    @Column(nullable = false)
    private String nome;

    // Valida celular com DDD (11 dígitos), exigindo o 9 inicial e aceitando formatação opcional como (XX) e hífen.
    @Pattern(regexp = "^\\(?\\d{2}\\)? ?9\\d{4}-?\\d{4}$", message = "Telefone inválido. Telefone tem que ter 11 números.")
    private String fone;

    @NotBlank(message = "O login é obrigatório.")
    @Column(unique = true, nullable = false)
    private String login;

    @NotBlank(message = "A senha é obrigatória.")
    @Size(min = 5, message = "A senha deve ter pelo menos 5 caracteres.")
    @Column(nullable = false)
    private String senha;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            foreignKey = @ForeignKey(name = "fk_user_roles", foreignKeyDefinition = "FOREIGN KEY (user_id) REFERENCES usuario(id) ON DELETE CASCADE")
    )
    @Column(name = "role")
    private Set<String> role;
}