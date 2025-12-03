package br.edu.ifpb.pautax.application.useCases.coordenador.processo.distribuir;

public interface IDistribuirProcessoUseCase {
    String execute(Integer idProcesso, Integer idRelator);
}
