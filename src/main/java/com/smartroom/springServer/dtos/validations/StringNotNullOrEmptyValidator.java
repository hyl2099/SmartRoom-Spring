package com.smartroom.springServer.dtos.validations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class StringNotNullOrEmptyValidator implements ConstraintValidator<StringNotNullOrEmpty, String> {

    @Override
    public void initialize(StringNotNullOrEmpty constraintAnnotation) {

    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return s != null && !s.isEmpty();
    }
}
