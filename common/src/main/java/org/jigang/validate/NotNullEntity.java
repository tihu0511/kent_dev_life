package org.jigang.validate;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by wujigang on 16/7/13.
 */
@Target({ TYPE, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = { NotNullEntityValiadtor.class })
@Documented
public @interface NotNullEntity {
    String message();

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
