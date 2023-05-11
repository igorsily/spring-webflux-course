package com.br.igorsily.webfluxcourse.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(
        validatedBy = {TrimStringValidator.class}
)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
public @interface TrimString {

    String message() default "{webflux-course.validationBlankSpacesBeginningAndEnd}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
