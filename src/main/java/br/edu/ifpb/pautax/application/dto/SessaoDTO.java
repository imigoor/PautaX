package br.edu.ifpb.pautax.application.dto;

import br.edu.ifpb.pautax.domain.enums.StatusReuniao;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
public class SessaoDTO {

    private Integer id;
    private LocalDateTime dataReuniao;
    private StatusReuniao status;
    private String colegiado;

    private List<ProcessoResumoDTO> processos;
}