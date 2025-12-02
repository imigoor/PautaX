package br.edu.ifpb.pautax.domain.validators;

import br.edu.ifpb.pautax.domain.entities.Colegiado;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PeriodoValidator implements ConstraintValidator<PeriodoValido, Colegiado> {

    @Override
    public void initialize(PeriodoValido constraintAnnotation) {
        // Inicialização se necessária
    }

    @Override
    public boolean isValid(Colegiado colegiado, ConstraintValidatorContext context) {
        if (colegiado == null) {
            return true;
        }

        if (colegiado.getDataInicio() == null || colegiado.getDataFim() == null) {
            return true;
        }

        return !colegiado.getDataInicio().after(colegiado.getDataFim());
    }
}
