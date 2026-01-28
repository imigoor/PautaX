package br.edu.ifpb.pautax.application.dto;

import br.edu.ifpb.pautax.domain.enums.TipoVoto;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class VotoProfessorDTO {
    private String nomeProfessor;
    private TipoVoto voto;
}
