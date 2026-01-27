package br.edu.ifpb.pautax.api.coordenador;

import br.edu.ifpb.pautax.application.useCases.coordenador.processo.distribuir.IDistribuirProcessoUseCase;
import br.edu.ifpb.pautax.application.useCases.coordenador.processo.listarPendentes.IListarProcessosPendentesUseCase;
import br.edu.ifpb.pautax.application.useCases.coordenador.processo.listarProcesso.IListarProcessosUseCase;
import br.edu.ifpb.pautax.application.useCases.coordenador.sessao.cadastrar.CriarSessaoFormDTO;
import br.edu.ifpb.pautax.application.useCases.coordenador.sessao.cadastrar.ICriarSessaoUseCase;
import br.edu.ifpb.pautax.application.useCases.coordenador.sessao.deletar.IDeletarSessaoUseCase;
import br.edu.ifpb.pautax.application.useCases.coordenador.sessao.iniciar.IIniciarSessaoUseCase;
import br.edu.ifpb.pautax.application.useCases.coordenador.sessao.listar.IListarSessaoUseCase;
import br.edu.ifpb.pautax.application.useCases.coordenador.sessao.mostrar.IMostrarSessaoUseCase;
import br.edu.ifpb.pautax.application.useCases.coordenador.sessao.processo.mostrar.IMostrarProcessoSessaoUseCase;
import br.edu.ifpb.pautax.application.useCases.coordenador.sessao.processo.votacao.concluir.IConcluirVotacaoUseCase;
import br.edu.ifpb.pautax.domain.enums.StatusProcesso;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/coordenador")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ROLE_COORDENADOR')")
public class CoordenadorController {
    private final IListarProcessosPendentesUseCase listarProcessosPendentesUseCase;
    private final IDistribuirProcessoUseCase distribuirProcessoUseCase;
    private final IListarProcessosUseCase listarProcessosUseCase;

    private final ICriarSessaoUseCase criarSessaoUseCase;
    private final IListarSessaoUseCase listarSessaoUseCase;
    private final IDeletarSessaoUseCase deletarSessaoUseCase;
    private final IIniciarSessaoUseCase iniciarSessaoUseCase;
    private final IMostrarSessaoUseCase mostrarSessaoUseCase;
    private final IMostrarProcessoSessaoUseCase mostrarProcessoSessaoUseCase;
    private final IConcluirVotacaoUseCase concluirVotacaoUseCase;

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

    @PostMapping("/criar-sessao")
    public String criarSessao(@Valid @ModelAttribute("novaReuniao") CriarSessaoFormDTO sessao,
                              BindingResult bindingResult,
                              Model model)
    {
        if (bindingResult.hasErrors())
        {
            listarSessaoUseCase.carregarDadosFormulario(model);
            return "/coordenador/listar-sessoes";
        }

        return criarSessaoUseCase.execute(sessao);
    }

    @GetMapping("/listar-sessoes")
    public ModelAndView mostrarSessoes()
    {
        return listarSessaoUseCase.execute();
    }

    @PostMapping("/deletar-sessao/{id}")
    public String deletarSessao(@PathVariable("id") Integer idReuniao)
    {
        return deletarSessaoUseCase.execute(idReuniao);
    }

    @PostMapping("/iniciar-sessao/{id}")
    public String iniciarSessao(@PathVariable("id") Integer idReuniao, RedirectAttributes redirectAttributes) {

        try {
            return iniciarSessaoUseCase.execute(idReuniao);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("erro", e.getMessage());
            return "redirect:/coordenador/listar-sessoes";
        }
    }

    @GetMapping("/conduzir-sessao/{id}")
    public ModelAndView mostrarConduzir(@PathVariable("id") Integer idReuniao) {
        return mostrarSessaoUseCase.execute(idReuniao);
    }

    @GetMapping("/conduzir-sessao/{id}/processo/{pid}")
    public ModelAndView mostrarProcessoDaSessao(@PathVariable("id") Integer idSessao,
                                                @PathVariable("pid") Integer idProcesso) {
        return mostrarProcessoSessaoUseCase.execute(idSessao, idProcesso);
    }

    @PostMapping("/conduzir-sessao/{id}/processo/{pid}/concluir")
    public String concluirVotacaoProcesso(@PathVariable("id") Integer idReuniao, @PathVariable("pid") Integer idProcesso, RedirectAttributes redirectAttributes) {
        try {
            concluirVotacaoUseCase.execute(idProcesso);
            redirectAttributes.addFlashAttribute("sucesso", "Votação concluída com sucesso.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("erro", e.getMessage());
            return "redirect:/coordenador/listar-sessoes";
        }

        return "redirect:/coordenador/conduzir-sessao/" + idReuniao;
    }
}
