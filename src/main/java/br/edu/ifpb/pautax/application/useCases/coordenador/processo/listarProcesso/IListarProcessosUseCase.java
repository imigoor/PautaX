package br.edu.ifpb.pautax.application.useCases.coordenador.processo.listarProcesso;

import br.edu.ifpb.pautax.domain.enums.StatusProcesso;
import org.springframework.data.domain.Pageable;
import org.springframework.web.servlet.ModelAndView;

public interface IListarProcessosUseCase {
    ModelAndView execute(StatusProcesso status, Integer idAluno, Integer idRelator, Pageable pageable);
}
