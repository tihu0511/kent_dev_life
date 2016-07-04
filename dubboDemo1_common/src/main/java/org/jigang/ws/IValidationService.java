package org.jigang.ws;

import org.jigang.dto.TestInDto;

/**
 * Created by wujigang on 16/7/1.
 */
public interface IValidationService {
    /**
     * 参数验证demo接口
     */
    @interface ValidateParam{}
    void validateParam(TestInDto inDto);
}
