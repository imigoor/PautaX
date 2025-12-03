package br.edu.ifpb.pautax.application.useCases.aluno.processo;

import br.edu.ifpb.pautax.domain.entities.Processo;
import br.edu.ifpb.pautax.infrastructure.security.CustomUserDetails;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public interface ICadastrarProcessoUseCase {
    public String execute(Processo processo, MultipartFile arquivo, CustomUserDetails userDetails, RedirectAttributes rd);
}
