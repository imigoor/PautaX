package br.edu.ifpb.pautax.application.useCases.administrador.assuntosProcessos.deletar;

import br.edu.ifpb.pautax.infrastructure.repositories.AssuntoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeletarAssuntosProcessosUseCase implements IDeletarAssuntosProcessosUseCase {
    public final AssuntoRepository assuntoRepository;

    @Override
    public String execute(Integer id) {
        assuntoRepository.deleteById(id);

        return "redirect:/admin/assuntos";
    }
}
