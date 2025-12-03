package br.edu.ifpb.pautax.infrastructure.repositories.specifications;

import br.edu.ifpb.pautax.domain.entities.Aluno;
import br.edu.ifpb.pautax.domain.entities.Assunto;
import br.edu.ifpb.pautax.domain.entities.Processo;
import br.edu.ifpb.pautax.domain.enums.StatusProcesso;
import org.springframework.data.jpa.domain.Specification;

public class ProcessoSpecification {
    public static Specification<Processo> filter(StatusProcesso status, Assunto assunto, Aluno aluno, String sort) {
        return (root, query, criteriaBuilder) -> {
            var predicates = criteriaBuilder.conjunction();

            if (status != null) {
                predicates = criteriaBuilder.and(predicates,
                        criteriaBuilder.equal(root.get("statusProcesso"), status));
            }

            if (assunto != null) {
                predicates = criteriaBuilder.and(predicates,
                        criteriaBuilder.equal(root.get("assunto"), assunto));
            }

            if (aluno != null) {
                predicates = criteriaBuilder.and(predicates,
                        criteriaBuilder.equal(root.get("interessado"), aluno));
            }

            if (sort != null && !sort.isBlank()) {

                switch (sort) {
                    case "dataRecepcaoAsc":
                        query.orderBy(criteriaBuilder.asc(root.get("dataRegistro")));
                        break;
                    case "dataRecepcaoDesc":
                        query.orderBy(criteriaBuilder.desc(root.get("dataRegistro")));
                        break;
                    default:
                        query.orderBy(criteriaBuilder.desc(root.get("id")));
                }
            } else {
                query.orderBy(criteriaBuilder.desc(root.get("id")));
            }

            return predicates;
        };
    }

    public static Specification<Processo> filterByCoordenador(StatusProcesso status, Integer alunoId, Integer relatorId) {
        return (root, query, criteriaBuilder) -> {// Cria um predicado inicial "verdadeiro" (AND vazio)
            var predicates = criteriaBuilder.conjunction();

            if (status != null) {
                predicates = criteriaBuilder.and(predicates,
                        criteriaBuilder.equal(root.get("statusProcesso"), status));
            }

            if (alunoId != null) {
                predicates = criteriaBuilder.and(predicates,
                        criteriaBuilder.equal(root.get("interessado").get("id"), alunoId));
            }

            if (relatorId != null) {
                predicates = criteriaBuilder.and(predicates,
                        criteriaBuilder.equal(root.get("relator").get("id"), relatorId));
            }

            query.orderBy(criteriaBuilder.desc(root.get("id")));

            return predicates;
        };
    }
}
