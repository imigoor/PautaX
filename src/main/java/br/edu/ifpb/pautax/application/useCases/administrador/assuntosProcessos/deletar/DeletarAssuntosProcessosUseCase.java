package br.edu.ifpb.pautax.application.useCases.administrador.assuntosProcessos.deletar;

import br.edu.ifpb.pautax.domain.entities.Assunto;
import br.edu.ifpb.pautax.infrastructure.repositories.AssuntoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

@Service
@RequiredArgsConstructor
public class DeletarAssuntosProcessosUseCase implements IDeletarAssuntosProcessosUseCase {
    public final AssuntoRepository assuntoRepository;

    @Override
    public ModelAndView execute(Integer id) {
        ModelAndView modelAndView = new ModelAndView("administrador/gerenciar-assuntos");

        assuntoRepository.deleteById(id);

        modelAndView.addObject("assunto", new Assunto());

        modelAndView.addObject("listaDeAssuntos", assuntoRepository.findAll());

        return modelAndView;
    }
}
