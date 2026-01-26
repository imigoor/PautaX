package br.edu.ifpb.pautax.domain.validators.matricula;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MatriculaValidator.class)
public @interface IMatricula {
    String message() default "A matrícula deve começar com o ano atual e conter 12 dígitos.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}