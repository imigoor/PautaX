package br.edu.ifpb.pautax.application.useCases.administrador.assuntosProcessos.cadastrar;

import br.edu.ifpb.pautax.domain.entities.Assunto;

public interface ICadastrarAssuntosProcessosUseCase {
    public String execute(Assunto assunto);
}
