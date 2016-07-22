package org.jigang.validate;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by wujigang on 16/7/13.
 */
public class MultiCountryAddressValidator implements ConstraintValidator<Address, Object> {
    public void initialize(Address constraintAnnotation) {
        // initialize the zipcode/city/country correlation service
        System.out.println(".....");
    }

    /**
     * Validate zipcode and city depending on the country
     */
    public boolean isValid(Object object, ConstraintValidatorContext context) {
        if (!(object instanceof Address)) {
            throw new IllegalArgumentException("@Address only applies to Address");
        }
        Address address = (Address) object;

        return address == null;
    }
}
