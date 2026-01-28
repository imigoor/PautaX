package br.edu.ifpb.pautax.application.dto;

import br.edu.ifpb.pautax.domain.enums.TipoDecisao;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProcessoVotacaoDTO {
    private Integer id;
    private String numero;
    private String assunto;
    private Integer relatorId;
    private String relatorNome;
    private TipoDecisao decisaoRelator;
    private Integer sessaoId;
}
