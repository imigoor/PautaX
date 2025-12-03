package br.edu.ifpb.pautax.application.useCases.administrador.colegiados.deletar;

import org.springframework.stereotype.Service;

import br.edu.ifpb.pautax.infrastructure.repositories.ColegiadoRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DeletarColegiadoUseCase implements IDeletarColegiadoUseCase {
    private final ColegiadoRepository colegiadoRepository;

    @Override
    public String execute(Integer id) {
        colegiadoRepository.deleteById(id);
        return "redirect:/admin/colegiados";
    }
}
