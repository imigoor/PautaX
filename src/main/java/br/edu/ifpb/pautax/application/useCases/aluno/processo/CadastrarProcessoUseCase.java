package br.edu.ifpb.pautax.application.useCases.aluno.processo;

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
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class CadastrarProcessoUseCase implements ICadastrarProcessoUseCase {
    private final ProcessoRepository processoRepository;
    private final AlunoRepository alunoRepository;

    @Override
    public String execute(Processo processo, MultipartFile arquivo, CustomUserDetails userDetails, RedirectAttributes rd) {
        try
        {
            Usuario usuario = userDetails.getUsuario();
            Aluno aluno = alunoRepository.findByUsuario(usuario)
                    .orElseThrow(() -> new RuntimeException("Aluno interessado não encontrado."));

            processo.setInteressado(aluno);
            processo.setRequerimento(arquivo.getBytes());
            processo.setStatusProcesso(StatusProcesso.CRIADO);
            processo.setNumero(gerarNumeroProcesso());
            processo.setDataRecepcao(LocalDate.now());

            processoRepository.save(processo);

            rd.addFlashAttribute("statusCadastroProcesso", "Processo cadastrado com sucesso!!");

            return "redirect:/aluno/home-aluno";

        } catch (IOException e) {
            throw new RuntimeException("Falha ao processar o arquivo de requerimento.", e);
        } catch (RuntimeException e) {
            throw new RuntimeException("Erro de dados ao cadastrar processo.", e);
        }
    }

    /**
     * Gera um número de processo único com base no ano + ID sequencial.
     */
    private String gerarNumeroProcesso() {
        String prefixo = "PROC";

        String ano = String.valueOf(java.time.Year.now());

        long quantidade = processoRepository.count() + 1;

        return String.format("%s-%s-%06d", prefixo, ano, quantidade);
    }
}
