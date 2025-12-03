package br.edu.ifpb.pautax.application.useCases.coordenador.processo.listarProcesso;

import br.edu.ifpb.pautax.domain.entities.Processo;
import br.edu.ifpb.pautax.domain.enums.StatusProcesso;
import br.edu.ifpb.pautax.infrastructure.repositories.AlunoRepository;
import br.edu.ifpb.pautax.infrastructure.repositories.ProcessoRepository;
import br.edu.ifpb.pautax.infrastructure.repositories.ProfessorRepository;
import br.edu.ifpb.pautax.infrastructure.repositories.specifications.ProcessoSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

@Service
@RequiredArgsConstructor
public class ListarProcessosUseCase implements IListarProcessosUseCase{
    private final ProcessoRepository processoRepository;
    private final AlunoRepository alunoRepository;
    private final ProfessorRepository professorRepository;

    @Override
    @Transactional
    public ModelAndView execute(StatusProcesso status, Integer idAluno, Integer idRelator) {
        ModelAndView mv = new ModelAndView("coordenador/listar-processos");

        Specification<Processo> spec = ProcessoSpecification.filterByCoordenador(status, idAluno, idRelator);

        mv.addObject("listaProcessos", processoRepository.findAll(spec));

        mv.addObject("listaAlunos", alunoRepository.findAll());
        mv.addObject("listaProfessores", professorRepository.findAll());

        mv.addObject("statusSelecionado", status);
        mv.addObject("alunoSelecionado", idAluno);
        mv.addObject("relatorSelecionado", idRelator);

        return mv;
    }
}
