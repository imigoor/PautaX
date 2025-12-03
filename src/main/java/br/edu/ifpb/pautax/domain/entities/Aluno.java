    package br.edu.ifpb.pautax.domain.entities;

    import jakarta.persistence.*;
    import jakarta.validation.Valid;
    import jakarta.validation.constraints.*;
    import lombok.Data;

    import java.util.List;

    @Entity
    @Data
    public class Aluno {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;

        @OneToOne(cascade = CascadeType.REMOVE)
        @JoinColumn(name = "usuario_id", nullable = false)
        @Valid
        Usuario usuario;

        @NotBlank(message = "A matrícula é obrigatória.")
        @Size(min = 4, max = 20, message = "A matrícula deve ter entre 4 e 20 caracteres.")
        @Column(nullable = false)
        private String matricula;

        @OneToMany(mappedBy = "interessado")
        private List<Processo> processos;
    }
