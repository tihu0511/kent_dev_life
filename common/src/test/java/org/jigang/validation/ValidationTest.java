package org.jigang.validation;

import org.jigang.date.DateUtil;
import org.jigang.validate.Pojo;
import org.jigang.validate.ValidationResult;
import org.jigang.validate.ValidationUtil;

import java.util.Date;

/**
 * Created by wujigang on 16/7/11.
 */
public class ValidationTest {
    public static void main(String[] args) {
//        ValidatePojo pojo = new ValidatePojo();
//        pojo.setId(null);
//        pojo.setName("alsdjflaksjdflasjd");
//        pojo.setDay(DateUtil.addDays(new Date(), 1));
//
//        ValidationResult result = ValidationUtil.validateEntity(pojo, false);


        Pojo p = new Pojo();
        ValidationResult result = ValidationUtil.validateEntity(p, false);

        if (!result.isPassed()) {
            System.out.println(result.getValidateErrorMsg());
        }
    }
}
