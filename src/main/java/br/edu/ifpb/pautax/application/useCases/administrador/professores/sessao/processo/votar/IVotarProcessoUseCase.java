package br.edu.ifpb.pautax.application.useCases.administrador.professores.sessao.processo.votar;

import br.edu.ifpb.pautax.domain.enums.TipoVoto;
import org.springframework.transaction.annotation.Transactional;

public interface IVotarProcessoUseCase {
    @Transactional
    String executar(int processoId,
                  int professorId,
                  TipoVoto tipoVoto);
}
