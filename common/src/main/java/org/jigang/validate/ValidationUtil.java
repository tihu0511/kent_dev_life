package org.jigang.validate;

import org.jigang.collection.CollectionUtil;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.groups.Default;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 参数校验工具
 *
 * Created by wujigang on 16/7/11.
 */
public class ValidationUtil {
    private static Validator validator =  Validation.buildDefaultValidatorFactory().getValidator();

    /**
     * 校验实体对象
     *
     * @param obj 校验的对象
     * @param isFetchOneMsg 是否只需要获取一条错误信息
     * @param <T>
     * @return
     */
    public static <T> ValidationResult validateEntity(T obj, boolean isFetchOneMsg){
        ValidationResult result = new ValidationResult();
        Set<ConstraintViolation<T>> set = validator.validate(obj, Default.class);

        if (CollectionUtil.isEmpty(set)) {
            return new ValidationResult();
        }

        result.setPassed(false);

        Map<String,String> errorMsg = new HashMap<String,String>();
        for(ConstraintViolation<T> cv : set){
            errorMsg.put(cv.getPropertyPath().toString(), cv.getMessage());

            //只获取一条错误信息
            if (isFetchOneMsg) {
                break;
            }
        }
        result.setErrorMsg(errorMsg);

        return result;
    }
}
