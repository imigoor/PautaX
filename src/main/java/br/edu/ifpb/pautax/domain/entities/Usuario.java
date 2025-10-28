package br.edu.ifpb.pautax.domain.entities;

import jakarta.persistence.*;
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

    @Column(nullable = false)
    private String nome;
    private String fone;

    @Column(unique = true, nullable = false)
    private String login;

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