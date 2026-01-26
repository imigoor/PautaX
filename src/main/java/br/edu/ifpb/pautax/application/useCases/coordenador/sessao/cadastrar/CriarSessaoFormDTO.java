package br.edu.ifpb.pautax.application.useCases.coordenador.sessao.cadastrar;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Data
public class CriarSessaoFormDTO {
    @NotNull(message = "A data da reunião é obrigatória.")
    @Future(message = "A data deve estar no futuro.")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private Date dataReuniao;

    @NotNull(message = "Você deve selecionar um colegiado.")
    private Integer colegiadoId;

    @NotEmpty(message = "A pauta não pode estar vazia.")
    private List<Integer> processosIds;
}
