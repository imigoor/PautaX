package br.edu.ifpb.pautax.application.useCases.professor.processos.parecer;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import br.edu.ifpb.pautax.domain.entities.Processo;
import br.edu.ifpb.pautax.infrastructure.repositories.ProcessoRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ExibirFormularioParecerUseCase implements IExibirFormularioParecerUseCase {
    private final ProcessoRepository processoRepository;

    @Override
    public ModelAndView execute(Integer idProcesso) {
        Processo processo = processoRepository.findById(idProcesso)
                .orElseThrow(() -> new RuntimeException("Processo não encontrado"));
        
        String parecerTexto = "";
        if (processo.getParecer() != null) {
            parecerTexto = new String(processo.getParecer());
        }

        ModelAndView mv = new ModelAndView("professor/emitir-parecer");
        mv.addObject("processo", processo);
        mv.addObject("parecerTexto", parecerTexto);
        return mv;
    }
}
