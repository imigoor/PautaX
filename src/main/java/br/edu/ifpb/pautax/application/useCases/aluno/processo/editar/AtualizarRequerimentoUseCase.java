package br.edu.ifpb.pautax.application.useCases.aluno.processo.editar;

import br.edu.ifpb.pautax.domain.entities.Aluno;
import br.edu.ifpb.pautax.domain.entities.Processo;
import br.edu.ifpb.pautax.domain.entities.Usuario;
import br.edu.ifpb.pautax.domain.enums.StatusProcesso;
import br.edu.ifpb.pautax.infrastructure.repositories.AlunoRepository;
import br.edu.ifpb.pautax.infrastructure.repositories.ProcessoRepository;
import br.edu.ifpb.pautax.infrastructure.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class AtualizarRequerimentoUseCase implements IAtualizarRequerimentoUseCase{
    private final ProcessoRepository processoRepository;

    @Override
    public String execute(int idProcesso, MultipartFile arquivo, CustomUserDetails userDetails, RedirectAttributes rd) {
        Processo processo = processoRepository.findById(idProcesso)
                .orElseThrow(() -> new RuntimeException("Processo não encontrado"));

        if (processo.getStatusProcesso() != StatusProcesso.CRIADO) {
            rd.addFlashAttribute("mensagemErro", "Não é permitido alterar o requerimento. O processo já saiu do status CRIADO.");
            return "redirect:/aluno/gerenciar-processo";
        }

        try {
            processo.setRequerimento(arquivo.getBytes());
            processoRepository.save(processo);

            rd.addFlashAttribute("mensagemSucesso", "Requerimento atualizado com sucesso!");
        } catch (IOException e) {
            rd.addFlashAttribute("mensagemErro", "Erro técnico ao processar o arquivo PDF.");
        }

        return "redirect:/aluno/gerenciar-processo";
    }
}
