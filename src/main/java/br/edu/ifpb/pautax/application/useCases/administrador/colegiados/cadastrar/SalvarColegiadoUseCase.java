package br.edu.ifpb.pautax.application.useCases.administrador.colegiados.cadastrar;

import org.springframework.stereotype.Service;

import br.edu.ifpb.pautax.domain.entities.Colegiado;
import br.edu.ifpb.pautax.infrastructure.repositories.ColegiadoRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SalvarColegiadoUseCase implements ISalvarColegiadoUseCase {
    private final ColegiadoRepository colegiadoRepository;

    @Override
    public String execute(Colegiado colegiado) {
        colegiadoRepository.save(colegiado);
        return "redirect:/admin/colegiados";
    }
}
