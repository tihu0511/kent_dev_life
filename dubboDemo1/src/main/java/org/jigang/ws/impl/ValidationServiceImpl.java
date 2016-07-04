package org.jigang.ws.impl;

import org.jigang.dto.TestInDto;
import org.jigang.ws.IValidationService;
import org.springframework.stereotype.Service;

/**
 * Created by wujigang on 16/7/1.
 */
@Service("validationService")
public class ValidationServiceImpl implements IValidationService {
    public void validateParam(TestInDto inDto) {
        System.out.println(inDto.getId());
        System.out.println(inDto.getName());
    }
}
