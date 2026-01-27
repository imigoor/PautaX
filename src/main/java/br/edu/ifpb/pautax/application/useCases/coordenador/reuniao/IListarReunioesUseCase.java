package br.edu.ifpb.pautax.application.useCases.coordenador.reuniao;

import java.util.List;

import br.edu.ifpb.pautax.domain.entities.Reuniao;
import br.edu.ifpb.pautax.domain.enums.StatusReuniao;

public interface IListarReunioesUseCase {
    List<Reuniao> execute(StatusReuniao status);
}
