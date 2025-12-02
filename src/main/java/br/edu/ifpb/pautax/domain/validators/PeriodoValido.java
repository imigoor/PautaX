package br.edu.ifpb.pautax.domain.validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PeriodoValidator.class)
public @interface PeriodoValido {
    String message() default "A data de início deve ser anterior à data de fim";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}