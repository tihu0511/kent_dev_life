package org.jigang.validate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by wujigang on 16/7/13.
 */
public class NotNullEntityValiadtor implements ConstraintValidator<NotNullEntity, Pojo> {
    public void initialize(NotNullEntity constraintAnnotation) {
        System.out.println("实例化NotNullEntityValidator");
    }

    public boolean isValid(Pojo value, ConstraintValidatorContext context) {
        System.out.println("开始校验");
        return null != value;
    }
}
