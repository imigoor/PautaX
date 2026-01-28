package br.edu.ifpb.pautax.application.useCases.coordenador.sessao.encerrar;

import br.edu.ifpb.pautax.domain.entities.Reuniao;
import br.edu.ifpb.pautax.domain.enums.StatusReuniao;
import br.edu.ifpb.pautax.infrastructure.repositories.ReuniaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Service
@RequiredArgsConstructor
public class EncerrarSessaoUseCase implements IEncerrarSessaoUseCase {
    private final ReuniaoRepository reuniaoRepository;

    @Override
    @Transactional
    public String execute(Integer idSessao, RedirectAttributes redirectAttributes) {
        Reuniao reuniao = reuniaoRepository.findById(idSessao).orElseThrow(() -> new IllegalArgumentException("Sessão não encontrada"));

        reuniao.setStatus(StatusReuniao.ENCERRADA);

        redirectAttributes.addFlashAttribute("sucesso", "Sessão encerrada com sucesso.");

        return "redirect:/coordenador/listar-sessoes";
    }
}
