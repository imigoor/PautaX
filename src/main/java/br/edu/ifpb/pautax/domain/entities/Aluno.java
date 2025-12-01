    package br.edu.ifpb.pautax.domain.entities;

    import jakarta.persistence.*;
    import lombok.Data;
    import lombok.EqualsAndHashCode;

    import java.util.List;

    @Entity
    @Data
    public class Aluno {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;

        @OneToOne(cascade = CascadeType.REMOVE)
        @JoinColumn(name = "usuario_id", nullable = false)
        Usuario usuario;

        @Column(nullable = false)
        private String matricula;

        @OneToMany(mappedBy = "interessado")
        private List<Processo> processos;
    }
