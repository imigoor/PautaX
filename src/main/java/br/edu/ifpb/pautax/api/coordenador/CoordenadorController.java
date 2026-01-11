package br.edu.ifpb.pautax.api.coordenador;

import br.edu.ifpb.pautax.application.useCases.coordenador.processo.distribuir.IDistribuirProcessoUseCase;
import br.edu.ifpb.pautax.application.useCases.coordenador.processo.listarPendentes.IListarProcessosPendentesUseCase;
import br.edu.ifpb.pautax.application.useCases.coordenador.processo.listarProcesso.IListarProcessosUseCase;
import br.edu.ifpb.pautax.application.useCases.professor.processos.listar.IListarProcessosAtribuidosUseCase;
import br.edu.ifpb.pautax.domain.enums.StatusProcesso;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/coordenador")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ROLE_COORDENADOR')")
public class CoordenadorController {
    private final IListarProcessosPendentesUseCase listarProcessosPendentesUseCase;
    private final IDistribuirProcessoUseCase distribuirProcessoUseCase;
    private final IListarProcessosUseCase listarProcessosUseCase;

    @GetMapping("/home-coordenador")
    public ModelAndView mostrarHomeCoordenador() {
        return new ModelAndView("coordenador/home-coordenador");
    }

    @GetMapping("/listar-pendentes")
    public ModelAndView mostrarDistribuirProcesso(@RequestParam(defaultValue="0") int page,
                                                  @RequestParam(defaultValue = "5") int size) {
        Pageable pageable = PageRequest.of(page, size);

        return listarProcessosPendentesUseCase.execute(pageable);
    }

    @GetMapping("/listar-processo")
    public ModelAndView listarTodosOsProcessos(@RequestParam(defaultValue="0") int page,
                                               @RequestParam(defaultValue = "5") int size,
                                               @RequestParam(value = "status", required = false) StatusProcesso status,
                                               @RequestParam(value = "idAluno", required = false) Integer idAluno,
                                               @RequestParam(value = "idRelator", required = false) Integer idRelator) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").ascending());

        return listarProcessosUseCase.execute(status, idAluno, idRelator, pageable);
    }

    @PostMapping("/distribuir-processo")
    public String distribuirProcesso(@RequestParam("idProcesso") Integer idProcesso,
                                               @RequestParam("idRelator") Integer idRelator) {
        return distribuirProcessoUseCase.execute(idProcesso, idRelator);
    }
}
