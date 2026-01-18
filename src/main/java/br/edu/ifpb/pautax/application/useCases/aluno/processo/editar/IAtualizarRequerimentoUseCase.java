package br.edu.ifpb.pautax.application.useCases.aluno.processo.editar;

import br.edu.ifpb.pautax.infrastructure.security.CustomUserDetails;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public interface IAtualizarRequerimentoUseCase {
    String execute(int idProcesso, MultipartFile arquivo, CustomUserDetails userDetails, RedirectAttributes rd);
}
