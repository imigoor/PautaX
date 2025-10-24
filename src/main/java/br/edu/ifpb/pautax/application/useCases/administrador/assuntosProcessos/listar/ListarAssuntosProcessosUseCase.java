package br.edu.ifpb.pautax.application.useCases.administrador.assuntosProcesso;

import br.edu.ifpb.pautax.domain.entities.Assunto;
import br.edu.ifpb.pautax.infrastructure.repositories.AssuntoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

@Service
@RequiredArgsConstructor
public class ListarAssuntosProcessosUseCase implements IlistarAssuntosProcessosUseCase {
    public final AssuntoRepository assuntoRepository;

    @Override
    public ModelAndView execute() {
        ModelAndView modelAndView = new ModelAndView("administrador/gerenciar-assuntos");

        modelAndView.addObject("listaDeAssuntos", assuntoRepository.findAll());

        modelAndView.addObject("assunto", new Assunto());

        return modelAndView;
    }
}
