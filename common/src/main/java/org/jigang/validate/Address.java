package org.jigang.validate;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by wujigang on 16/7/13.
 */
//@ConstraintValidator(MultiCountryAddressValidator.class)
@Target(TYPE)
@Retention(RUNTIME)
public @interface Address {
    String message() default "{error.address}";
    String[] groups() default {};
}
