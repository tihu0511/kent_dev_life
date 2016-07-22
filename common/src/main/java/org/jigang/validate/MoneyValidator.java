package org.jigang.validate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by wujigang on 16/7/11.
 */
public class MoneyValidator implements ConstraintValidator<Money, String> {
    public void initialize(Money params) {

    }

    public boolean isValid(String value, ConstraintValidatorContext context) {
        return false; //TODO
    }
}
