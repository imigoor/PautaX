package br.edu.ifpb.pautax.application.useCases.administrador.colegiados.cadastrar;

import br.edu.ifpb.pautax.domain.entities.Colegiado;

public interface ISalvarColegiadoUseCase {
    public String execute(Colegiado colegiado);
}
