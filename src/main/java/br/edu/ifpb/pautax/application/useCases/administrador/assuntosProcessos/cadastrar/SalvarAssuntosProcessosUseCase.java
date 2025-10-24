package br.edu.ifpb.pautax.application.useCases.administrador.assuntosProcessos.cadastrar;

import br.edu.ifpb.pautax.domain.entities.Assunto;
import br.edu.ifpb.pautax.infrastructure.repositories.AssuntoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SalvarAssuntosProcessosUseCase implements ISalvarAssuntosProcessosUseCase {
    public final AssuntoRepository assuntoRepository;

    @Override
    public String execute(Assunto assunto) {
        assuntoRepository.save(assunto);

        return "redirect:/admin/assuntos";
    }
}
