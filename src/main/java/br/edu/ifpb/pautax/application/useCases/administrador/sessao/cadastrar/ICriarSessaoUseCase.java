package br.edu.ifpb.pautax.application.useCases.administrador.sessao.cadastrar;

import br.edu.ifpb.pautax.domain.entities.Reuniao;

public interface ICriarSessaoUseCase {
    String execute(Reuniao sessao);
}
