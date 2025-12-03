package br.edu.ifpb.pautax.api.aluno;

import br.edu.ifpb.pautax.application.useCases.aluno.home.IMostrarHomeUseCase;
import br.edu.ifpb.pautax.application.useCases.aluno.processo.ICadastrarProcessoUseCase;
import br.edu.ifpb.pautax.application.useCases.aluno.processo.listar.IListarProcessoAlunoUseCase;
import br.edu.ifpb.pautax.domain.entities.Assunto;
import br.edu.ifpb.pautax.domain.entities.Processo;
import br.edu.ifpb.pautax.domain.entities.Usuario;
import br.edu.ifpb.pautax.domain.enums.StatusProcesso;
import br.edu.ifpb.pautax.infrastructure.security.CustomUserDetails;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.hibernate.service.spi.InjectService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;

@Controller
@RequestMapping("/aluno")
@RequiredArgsConstructor()
public class AlunoController {
    private final IMostrarHomeUseCase mostrarHomeUseCase;

    private final ICadastrarProcessoUseCase cadastrarProcessoUseCase;
    private final IListarProcessoAlunoUseCase listarProcessoAlunoUseCase;

    /**
     * Página inicial do aluno
     * <p>
     * Exibe a página inicial personalizada para o aluno autenticado.
     *
     * @param userDetails Detalhes do usuário autenticado. Obtido através da injeção do Spring Security com a anotação @AuthenticationPrincipal.
     * @return ModelAndView contendo a visão "home-aluno" e os dados do aluno.
     */
    @GetMapping("/home-aluno")
    public ModelAndView mostrarHomeAluno(@AuthenticationPrincipal CustomUserDetails userDetails) {
        return mostrarHomeUseCase.execute(userDetails);
    }

    @GetMapping("/gerenciar-processo")
    public ModelAndView gerenciarProcesso(@AuthenticationPrincipal CustomUserDetails userDetails,
                                          @RequestParam(value = "status", required = false) StatusProcesso status,
                                          @RequestParam(value = "assuntoId", required = false) Assunto assunto,
                                          @RequestParam(value = "sort", required = false) String sort) {
        return listarProcessoAlunoUseCase.execute(userDetails, status, assunto, sort);
    }

    @PostMapping("/cadastrar-processo")
    public String cadastrarProcesso(
            @Valid @ModelAttribute Processo processo,
            BindingResult result,
            @RequestParam("arquivoRequerimento") MultipartFile arquivo,
            @AuthenticationPrincipal CustomUserDetails userDetails,
            Model model,
            RedirectAttributes rd)
    {
        if (arquivo.isEmpty()) {
            result.rejectValue("requerimento", "error.processo", "O arquivo do requerimento é obrigatório.");
        }

        if (result.hasErrors()) {
            Map<String, Object> dadosDoUseCase = mostrarHomeUseCase.execute(userDetails).getModel();

            model.addAttribute("listaAssuntos", dadosDoUseCase.get("listaAssuntos"));
            model.addAttribute("aluno", dadosDoUseCase.get("aluno"));

            return "aluno/gerenciar-processo";
        }

        return cadastrarProcessoUseCase.execute(processo, arquivo, userDetails, rd);
    }
}
