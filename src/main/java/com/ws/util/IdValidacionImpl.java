package com.ws.util;

import org.springframework.web.bind.MethodArgumentNotValidException;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IdValidacionImpl implements ConstraintValidator<IdValidacion,Long> {

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {

        if(value.longValue() == 0)
            return false;
        else
            return true;
    }
}
