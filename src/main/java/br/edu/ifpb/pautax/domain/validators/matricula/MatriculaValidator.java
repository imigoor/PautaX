package br.edu.ifpb.pautax.domain.validators.matricula;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;

public class MatriculaValidator implements ConstraintValidator<IMatricula, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isBlank()) {
            return false;
        }

        String anoAtual = String.valueOf(LocalDate.now().getYear());

        // a regra é que deve começar com o ano atual e ter 12 dígitos no total, tipo: 202601030406 apenas numero
        String regex = anoAtual + "\\d{8}";

        return value.matches(regex);
    }
}
