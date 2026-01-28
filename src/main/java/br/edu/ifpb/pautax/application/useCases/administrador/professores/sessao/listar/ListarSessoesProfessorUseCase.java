package br.edu.ifpb.pautax.application.useCases.administrador.professores.sessao.listar;

import br.edu.ifpb.pautax.domain.entities.Professor;
import br.edu.ifpb.pautax.domain.entities.Reuniao;
import br.edu.ifpb.pautax.infrastructure.repositories.ProfessorRepository;
import br.edu.ifpb.pautax.infrastructure.repositories.ReuniaoRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ListarSessoesProfessorUseCase implements IListarSessoesProfessorUseCase {

    private final ProfessorRepository professorRepository;
    private final ReuniaoRepository reuniaoRepository;

    @Override
    @Transactional()
    public ModelAndView execute() {
        ModelAndView mv = new ModelAndView("/professor/listar-sessoes");

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String login = auth.getName();

        Professor professor = (Professor) professorRepository
                .findByUsuarioLogin(login)
                .orElseThrow(() -> new RuntimeException("Professor não encontrado"));

        List<Reuniao> reunioes = reuniaoRepository
                .findByColegiadoMembrosContains(professor);

        reunioes.forEach(r -> Hibernate.initialize(r.getProcessos()));

        mv.addObject("sessoes", reunioes);

        return mv;
    }
}