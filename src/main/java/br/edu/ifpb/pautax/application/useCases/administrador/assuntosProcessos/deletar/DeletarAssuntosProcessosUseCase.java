package br.edu.ifpb.pautax.application.useCases.administrador.assuntosProcessos.deletar;

import br.edu.ifpb.pautax.infrastructure.repositories.AssuntoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Service
@RequiredArgsConstructor
public class DeletarAssuntosProcessosUseCase implements IDeletarAssuntosProcessosUseCase {
    public final AssuntoRepository assuntoRepository;

    @Override
    @Transactional
    public String execute(Integer id, RedirectAttributes redirectAttributes) {
        var assunto = assuntoRepository.findById(id).orElse(null);

        if (assunto == null) {
            redirectAttributes.addFlashAttribute("erro", "Assunto não encontrado.");
            return "redirect:/admin/assuntos";
        }

        if (assunto.getProcessos() != null && !assunto.getProcessos().isEmpty()) {
            redirectAttributes.addFlashAttribute("erro",
                    "Não é possível excluir: existem processos vinculados a este assunto.");
            return "redirect:/admin/assuntos";
        }

        assuntoRepository.deleteById(id);

        return "redirect:/admin/assuntos";
    }
}
