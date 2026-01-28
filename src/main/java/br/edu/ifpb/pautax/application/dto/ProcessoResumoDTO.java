package br.edu.ifpb.pautax.application.dto;

import br.edu.ifpb.pautax.domain.enums.StatusProcesso;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProcessoResumoDTO {

    private Integer id;
    private String numero;
    private String assunto;
    private StatusProcesso status;
    private Integer relatorId;
    private String relatorNome;
}