package br.edu.ifpb.pautax.api.administrador;

import br.edu.ifpb.pautax.application.useCases.administrador.assuntosProcessos.cadastrar.ISalvarAssuntosProcessosUseCase;
import br.edu.ifpb.pautax.application.useCases.administrador.assuntosProcessos.deletar.IDeletarAssuntosProcessosUseCase;
import br.edu.ifpb.pautax.application.useCases.administrador.assuntosProcessos.editar.IEditarAssuntosProcessosUseCase;
import br.edu.ifpb.pautax.application.useCases.administrador.assuntosProcessos.listar.IlistarAssuntosProcessosUseCase;
import br.edu.ifpb.pautax.domain.entities.Assunto;
import br.edu.ifpb.pautax.domain.entities.Usuario;
import br.edu.ifpb.pautax.infrastructure.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor()
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {
    public final IlistarAssuntosProcessosUseCase listarAssuntosProcessosUseCase;
    public final ISalvarAssuntosProcessosUseCase salvarAssuntosProcessosUseCase;
    public final IEditarAssuntosProcessosUseCase editarAssuntosProcessosUseCase;
    public final IDeletarAssuntosProcessosUseCase deletarAssuntosProcessosUseCase;

    @GetMapping("/home-admin")
    public ModelAndView mostrarHomeAdmin(@AuthenticationPrincipal CustomUserDetails userDetails)
    {
        Usuario admin = userDetails.getUsuario();

        ModelAndView modelAndView = new ModelAndView("administrador/home-admin");
        modelAndView.addObject("admin", admin);

        return modelAndView;
    }

    @GetMapping("/assuntos")
    public ModelAndView mostrarGerenciarAssunto()
    {
        return listarAssuntosProcessosUseCase.execute();
    }

    @PostMapping("/assuntos/salvar")
    public String cadastrarAssunto(@ModelAttribute Assunto assunto)
    {
        return salvarAssuntosProcessosUseCase.execute(assunto);
    }

    @GetMapping("/assuntos/editar/{id}")
    public ModelAndView editarAssunto(@PathVariable Integer id)
    {
        return editarAssuntosProcessosUseCase.execute(id);
    }

    @PostMapping("/assuntos/deletar/{id}")
    public String deletarAssunto(@PathVariable Integer id)
    {
        return deletarAssuntosProcessosUseCase.execute(id);
    }
}
