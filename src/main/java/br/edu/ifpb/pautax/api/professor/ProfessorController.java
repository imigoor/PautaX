package br.edu.ifpb.pautax.api.professor;

import br.edu.ifpb.pautax.application.useCases.administrador.professores.sessao.listar.IListarSessoesProfessorUseCase;
import br.edu.ifpb.pautax.application.useCases.administrador.professores.sessao.mostrar.IvisualizarSessaoUseCase;
import br.edu.ifpb.pautax.application.useCases.administrador.professores.sessao.processo.visualizar.IVisualizarProcessoVotacaoUseCase;
import br.edu.ifpb.pautax.application.useCases.administrador.professores.sessao.processo.votar.IVotarProcessoUseCase;
import br.edu.ifpb.pautax.domain.enums.TipoVoto;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.edu.ifpb.pautax.application.useCases.professor.processos.listar.IListarProcessosAtribuidosUseCase;
import br.edu.ifpb.pautax.application.useCases.professor.processos.parecer.IEmitirParecerUseCase;
import br.edu.ifpb.pautax.application.useCases.professor.processos.parecer.IExibirFormularioParecerUseCase;
import br.edu.ifpb.pautax.domain.entities.Usuario;
import br.edu.ifpb.pautax.domain.enums.TipoDecisao;
import br.edu.ifpb.pautax.infrastructure.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/professor")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ROLE_PROFESSOR')")
public class ProfessorController {

    private final IListarProcessosAtribuidosUseCase listarProcessosAtribuidosUseCase;
    private final IListarSessoesProfessorUseCase listarSessoesProfessorUseCase;
    private final IvisualizarSessaoUseCase visualizarSessaoUseCase;
    private final IVisualizarProcessoVotacaoUseCase  visualizarProcessoVotacaoUseCase;
    private final IVotarProcessoUseCase votarProcessoUseCase;
    private final IExibirFormularioParecerUseCase exibirFormularioParecerUseCase;
    private final IEmitirParecerUseCase emitirParecerUseCase;

    @GetMapping("/home-professor")
    public ModelAndView mostrarHomeProfessor() {
        return new ModelAndView("professor/home-professor");
    }

    @GetMapping("/meus-processos")
    public ModelAndView mostrarMeusProcessos(@AuthenticationPrincipal CustomUserDetails userDetails) {

        Usuario professorLogado = userDetails.getUsuario();

        return listarProcessosAtribuidosUseCase.execute(professorLogado);
    }

    @GetMapping("/listar-sessoes")
    public ModelAndView listarSessoes() {
        return listarSessoesProfessorUseCase.execute();
    }

    @GetMapping("/visualizar-sessao/{id}")
    public ModelAndView visualizarVotacao(@PathVariable Integer id) {
        return visualizarSessaoUseCase.execute(id);
    }

    @GetMapping("/visualizar-sessao/{sid}/processo/{pid}")
    public ModelAndView visualizarProcessoVotacao(@PathVariable Integer pid) {
        return visualizarProcessoVotacaoUseCase.execute(pid);
    }

    @PostMapping("/visualizar-sessao/{sid}/votar/{pid}")
    public String votarProcesso(@PathVariable("sid") int sid,
                                @PathVariable("pid") int pid,
                                @RequestParam("voto") TipoVoto tipoVoto,
                                @RequestParam("justificativa") String justificativa,
                                @AuthenticationPrincipal CustomUserDetails userDetails) {
        Usuario usuario = userDetails.getUsuario();

        return votarProcessoUseCase.executar(pid, usuario.getId(), tipoVoto, justificativa);
    }

    @GetMapping("/processo/{id}/parecer")
    public ModelAndView exibirFormularioParecer(@PathVariable("id") Integer idProcesso) {
        return exibirFormularioParecerUseCase.execute(idProcesso);
    }

    @PostMapping("/processo/salvar-parecer")
    public String salvarParecer(@RequestParam("idProcesso") Integer idProcesso,
                                @RequestParam("decisao") TipoDecisao decisao,
                                @RequestParam("textoParecer") String textoParecer) {
        return emitirParecerUseCase.execute(idProcesso, decisao, textoParecer);
    }
}
