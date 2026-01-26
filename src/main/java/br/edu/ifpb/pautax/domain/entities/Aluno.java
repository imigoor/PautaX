    package br.edu.ifpb.pautax.domain.entities;

    import br.edu.ifpb.pautax.domain.validators.matricula.IMatricula;
    import jakarta.persistence.*;
    import jakarta.validation.Valid;
    import jakarta.validation.constraints.*;
    import lombok.Data;
    import lombok.ToString;

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

        @IMatricula
        @Column(nullable = false)
        private String matricula;

        @OneToMany(mappedBy = "interessado")
        @ToString.Exclude
        private List<Processo> processos;
    }
