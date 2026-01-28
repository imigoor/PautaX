package br.edu.ifpb.pautax.application.useCases.coordenador.sessao.listar;

import br.edu.ifpb.pautax.application.useCases.coordenador.sessao.cadastrar.CriarSessaoFormDTO;
import br.edu.ifpb.pautax.domain.entities.Reuniao;
import br.edu.ifpb.pautax.domain.enums.StatusReuniao;
import br.edu.ifpb.pautax.infrastructure.repositories.ColegiadoRepository;
import br.edu.ifpb.pautax.infrastructure.repositories.ProcessoRepository;
import br.edu.ifpb.pautax.infrastructure.repositories.ProfessorRepository;
import br.edu.ifpb.pautax.infrastructure.repositories.ReuniaoRepository;
import lombok.AllArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Service
@AllArgsConstructor
public class ListarSessaoUseCase implements IListarSessaoUseCase{

    private final ReuniaoRepository reuniaoRepository;
    private final ProcessoRepository processoRepository;
    private final ColegiadoRepository colegiadoRepository;
    private final ProfessorRepository professorRepository;

    @Override
    @Transactional(readOnly = true)
    public ModelAndView execute(StatusReuniao status) {

        ModelAndView mv = new ModelAndView("/coordenador/listar-sessoes");

        mv.addObject("novaReuniao", new CriarSessaoFormDTO());

        List<Reuniao> reunioes;

        if (status == null) {
            reunioes = reuniaoRepository.findAll();
        } else {
            reunioes = reuniaoRepository.findByStatus(status);
        }

        for (Reuniao r : reunioes) {
            Hibernate.initialize(r.getProcessos());
        }

        mv.addObject("sessao", reunioes);
        mv.addObject("statusSelecionado", status);

        mv.addObject("listaColegiados", colegiadoRepository.findAll());
        mv.addObject("processosDisponiveis",
                processoRepository.findByReuniaoIsNullAndRelatorIdIsNotNullAndDecisaoRelatorIsNotNull());
        mv.addObject("membrosDisponiveis", professorRepository.findAll());

        return mv;
    }

    @Override
    @Transactional(readOnly = true)
    public void carregarDadosFormulario(Model model) {
        List<Reuniao> reunioes = reuniaoRepository.findAll();
        for (Reuniao r : reunioes) {
            Hibernate.initialize(r.getProcessos());
        }
        model.addAttribute("sessao", reunioes);
        model.addAttribute("listaColegiados", colegiadoRepository.findAll());
        model.addAttribute("processosDisponiveis", processoRepository.findByReuniaoIsNullAndRelatorIdIsNotNull());
        model.addAttribute("membrosDisponiveis", professorRepository.findAll());
    }
}
