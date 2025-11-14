package br.edu.ifpb.pautax.infrastructure.repositories.specifications;

import br.edu.ifpb.pautax.domain.entities.Assunto;
import br.edu.ifpb.pautax.domain.entities.Processo;
import br.edu.ifpb.pautax.domain.enums.StatusProcesso;
import org.springframework.data.jpa.domain.Specification;

public class ProcessoSpecification {
    public static Specification<Processo> filter(StatusProcesso status, Assunto assunto) {
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

            return predicates;
        };
    }
}
