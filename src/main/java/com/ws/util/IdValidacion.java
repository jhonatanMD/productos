package com.ws.util;

import org.springframework.data.annotation.Id;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ METHOD, FIELD, ANNOTATION_TYPE ,PARAMETER})
@Retention(RUNTIME)
@Constraint(validatedBy = IdValidacionImpl.class)
@Documented
public @interface IdValidacion {

    String message() default "Dato Invalido";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
