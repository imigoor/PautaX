package br.edu.ifpb.pautax.application.useCases.professor.processos.parecer;

import br.edu.ifpb.pautax.domain.enums.TipoDecisao;

public interface IEmitirParecerUseCase {
    String execute(Integer idProcesso, TipoDecisao decisao, String textoParecer);
}
