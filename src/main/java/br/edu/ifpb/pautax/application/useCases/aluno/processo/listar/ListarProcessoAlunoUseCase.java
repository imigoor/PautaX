package br.edu.ifpb.pautax.application.useCases.aluno.processo.listar;

import br.edu.ifpb.pautax.domain.entities.Assunto;
import br.edu.ifpb.pautax.domain.entities.Processo;
import br.edu.ifpb.pautax.domain.enums.StatusProcesso;
import br.edu.ifpb.pautax.infrastructure.repositories.AssuntoRepository;
import br.edu.ifpb.pautax.infrastructure.repositories.ProcessoRepository;
import br.edu.ifpb.pautax.infrastructure.repositories.specifications.ProcessoSpecification;
import br.edu.ifpb.pautax.infrastructure.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

@Service
@RequiredArgsConstructor
public class ListarProcessoAlunoUseCase implements IListarProcessoAlunoUseCase{
    private final AssuntoRepository assuntoRepository;
    private final ProcessoRepository processoRepository;

    @Override
    public ModelAndView execute(CustomUserDetails userDetails, StatusProcesso status, Assunto assunto, String sort) {
        ModelAndView modelAndView = new ModelAndView("aluno/gerenciar-processo");

        Specification<Processo> spec = ProcessoSpecification.filter(status, assunto, sort);

        modelAndView.addObject("listaAssuntos", assuntoRepository.findAll());
        modelAndView.addObject("listaProcesso", processoRepository.findAll(spec));
        modelAndView.addObject("processo", new Processo());

        return modelAndView;
    }
}
