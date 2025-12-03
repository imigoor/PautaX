package br.edu.ifpb.pautax.application.useCases.administrador.assuntosProcessos.editar;

import br.edu.ifpb.pautax.infrastructure.repositories.AssuntoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

@Service
@RequiredArgsConstructor
public class EditarAssuntosProcessosUseCase implements IEditarAssuntosProcessosUseCase{
    public final AssuntoRepository assuntoRepository;

    @Override
    public ModelAndView execute(Integer id) {
        ModelAndView modelAndView = new ModelAndView("administrador/gerenciar-assuntos");

        modelAndView.addObject("assunto", assuntoRepository.findById(id).get());

        modelAndView.addObject("listaDeAssuntos", assuntoRepository.findAll());

        return modelAndView;
    }
}
