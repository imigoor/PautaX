package br.edu.ifpb.pautax.application.useCases.coordenador.sessao.cadastrar;

import br.edu.ifpb.pautax.domain.entities.Reuniao;

public interface ICriarSessaoUseCase {
    String execute(Reuniao sessao);
}
