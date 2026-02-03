package br.edu.ifpb.pautax.api.administrador;

import br.edu.ifpb.pautax.application.useCases.administrador.alunos.cadastrar.ISalvarAlunoUseCase;
import br.edu.ifpb.pautax.application.useCases.administrador.alunos.deletar.IDeletarAlunoUseCase;
import br.edu.ifpb.pautax.application.useCases.administrador.alunos.editar.IEditarAlunoUseCase;
import br.edu.ifpb.pautax.application.useCases.administrador.alunos.listar.IListarAlunoUseCase;
import br.edu.ifpb.pautax.application.useCases.administrador.assuntosProcessos.cadastrar.ISalvarAssuntosProcessosUseCase;
import br.edu.ifpb.pautax.application.useCases.administrador.assuntosProcessos.deletar.IDeletarAssuntosProcessosUseCase;
import br.edu.ifpb.pautax.application.useCases.administrador.assuntosProcessos.editar.IEditarAssuntosProcessosUseCase;
import br.edu.ifpb.pautax.application.useCases.administrador.assuntosProcessos.listar.IlistarAssuntosProcessosUseCase;
import br.edu.ifpb.pautax.application.useCases.administrador.colegiados.cadastrar.ISalvarColegiadoUseCase;
import br.edu.ifpb.pautax.application.useCases.administrador.colegiados.deletar.IDeletarColegiadoUseCase;
import br.edu.ifpb.pautax.application.useCases.administrador.colegiados.editar.IEditarColegiadoUseCase;
import br.edu.ifpb.pautax.application.useCases.administrador.colegiados.listar.IListarColegiadoUseCase;
import br.edu.ifpb.pautax.application.useCases.administrador.professores.cadastrar.ISalvarProfessorUseCase;
import br.edu.ifpb.pautax.application.useCases.administrador.professores.deletar.IDeletarProfessorUseCase;
import br.edu.ifpb.pautax.application.useCases.administrador.professores.editar.IEditarProfessorUseCase;
import br.edu.ifpb.pautax.application.useCases.administrador.professores.listar.IListarProfessorUseCase;
import br.edu.ifpb.pautax.domain.entities.Aluno;
import br.edu.ifpb.pautax.domain.entities.Assunto;
import br.edu.ifpb.pautax.domain.entities.Colegiado;
import br.edu.ifpb.pautax.domain.entities.Professor;
import br.edu.ifpb.pautax.domain.entities.Usuario;
import br.edu.ifpb.pautax.infrastructure.security.CustomUserDetails;
import jakarta.validation.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.naming.Binding;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor()
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {
    private final IlistarAssuntosProcessosUseCase listarAssuntosProcessosUseCase;
    private final ISalvarAssuntosProcessosUseCase salvarAssuntosProcessosUseCase;
    private final IEditarAssuntosProcessosUseCase editarAssuntosProcessosUseCase;
    private final IDeletarAssuntosProcessosUseCase deletarAssuntosProcessosUseCase;

    private final IListarAlunoUseCase listarAlunoUseCase;
    private final ISalvarAlunoUseCase salvarAlunoUseCase;
    private final IEditarAlunoUseCase editarAlunoUseCase;
    private final IDeletarAlunoUseCase deletarAlunoUseCase;

    private final IListarProfessorUseCase listarProfessorUseCase;
    private final ISalvarProfessorUseCase salvarProfessorUseCase;
    private final IEditarProfessorUseCase editarProfessorUseCase;
    private final IDeletarProfessorUseCase deletarProfessorUseCase;

    private final IListarColegiadoUseCase listarColegiadoUseCase;
    private final ISalvarColegiadoUseCase salvarColegiadoUseCase;
    private final IEditarColegiadoUseCase editarColegiadoUseCase;
    private final IDeletarColegiadoUseCase deletarColegiadoUseCase;

    //  -*-*- Home -*-*-
    @GetMapping("/home-admin")
    public ModelAndView mostrarHomeAdmin(@AuthenticationPrincipal CustomUserDetails userDetails)
    {
        Usuario admin = userDetails.getUsuario();

        ModelAndView modelAndView = new ModelAndView("administrador/home-admin");
        modelAndView.addObject("admin", admin);

        return modelAndView;
    }

    //  -*-*- Aluno -*-*-
    @GetMapping("/alunos")
    public ModelAndView mostrarAlunos()
    {
        ModelAndView modelAndView = listarAlunoUseCase.execute();
        modelAndView.addObject("aluno", new Aluno());
        return modelAndView;
    }

    @PostMapping("/alunos/salvar")
    public String cadastrarAluno(@Valid @ModelAttribute Aluno aluno, BindingResult result, Model model)
    {
        if (result.hasErrors())
        {
            model.addAttribute("listaDeAlunos", listarAlunoUseCase.execute().getModel().get("listaDeAlunos"));
            return "administrador/gerenciar-alunos";
        }

        return salvarAlunoUseCase.execute(aluno);
    }

    @GetMapping("/alunos/editar/{id}")
    public ModelAndView editarAluno(@PathVariable int id)
    {
        return editarAlunoUseCase.execute(id);
    }

    @PostMapping("/alunos/deletar/{id}")
    public String deletarAluno(@PathVariable int id, RedirectAttributes redirectAttributes)
    {
        return deletarAlunoUseCase.execute(id, redirectAttributes);
    }

//  -*-*- Professor -*-*-
    @GetMapping("/professores")
    public ModelAndView mostrarProfessores()
    {
        ModelAndView modelAndView = listarProfessorUseCase.execute();
        modelAndView.addObject("professor", new Professor());
        return modelAndView;
    }

    @PostMapping("/professores/salvar")
    public String cadastrarProfessor(@Valid @ModelAttribute Professor professor, BindingResult result, Model model)
    {
        if (result.hasErrors()) {

            model.addAttribute("listaDeProfessores", listarProfessorUseCase.execute().getModel().get("listaDeProfessores"));
            return "administrador/gerenciar-professores";
        }

        return salvarProfessorUseCase.execute(professor);
    }

    @GetMapping("/professores/editar/{id}")
    public ModelAndView editarProfessor(@PathVariable int id)
    {
        return editarProfessorUseCase.execute(id);
    }

    @PostMapping("/professores/deletar/{id}")
    public String deletarProfessor(@PathVariable int id)
    {
        return deletarProfessorUseCase.execute(id);
    }


//  -*-*- Assunto -*-*-
    @GetMapping("/assuntos")
    public ModelAndView mostrarGerenciarAssunto()
    {
        return listarAssuntosProcessosUseCase.execute();
    }

    @PostMapping("/assuntos/salvar")
    public String cadastrarAssunto(@Valid @ModelAttribute Assunto assunto, BindingResult result, Model model)
    {
        if (result.hasErrors()) {
            model.addAttribute("listaDeAssuntos", listarAssuntosProcessosUseCase.execute().getModel().get("listaDeAssuntos"));
            return "administrador/gerenciar-assuntos";
        }

        return salvarAssuntosProcessosUseCase.execute(assunto);
    }

    @GetMapping("/assuntos/editar/{id}")
    public ModelAndView editarAssunto(@PathVariable Integer id)
    {
        return editarAssuntosProcessosUseCase.execute(id);
    }

    @PostMapping("/assuntos/deletar/{id}")
    public String deletarAssunto(@PathVariable Integer id, RedirectAttributes redirectAttributes)
    {
        return deletarAssuntosProcessosUseCase.execute(id, redirectAttributes);
    }


    // -*-- Colegiado -*--
    @GetMapping("/colegiados")
    public ModelAndView mostrarGerenciarColegiado() {
        return listarColegiadoUseCase.execute();
    }

    @PostMapping("/colegiados/salvar")
    public String cadastrarColegiado(@Valid @ModelAttribute Colegiado colegiado, BindingResult result, Model model) {
        boolean temCoordenador = false;

        if (colegiado.getMembros() != null && !colegiado.getMembros().isEmpty()) {
            temCoordenador = colegiado.getMembros().stream()
                .anyMatch(professor -> professor.isCoordenador());
        }

        if (!temCoordenador) {
            result.rejectValue("membros", "erro.colegiado", "O colegiado deve ter pelo menos um professor Coordenador.");
        }

        if (result.hasErrors()) {
            var dadosTela = listarColegiadoUseCase.execute().getModel();
            
            model.addAttribute("listaDeColegiados", listarColegiadoUseCase.execute().getModel().get("listaDeColegiados"));
            model.addAttribute("todosProfessores", listarColegiadoUseCase.execute().getModel().get("todosProfessores"));
            model.addAttribute("todosAlunos", dadosTela.get("todosAlunos"));
            return "administrador/gerenciar-colegiados";
        }

        return salvarColegiadoUseCase.execute(colegiado);
    }

    @GetMapping("/colegiados/editar/{id}")
    public ModelAndView editarColegiado(@PathVariable Integer id) {
        return editarColegiadoUseCase.execute(id);
    }

    @PostMapping("/colegiados/deletar/{id}")
    public String deletarColegiado(@PathVariable Integer id) {
        return deletarColegiadoUseCase.execute(id);
    }
}
