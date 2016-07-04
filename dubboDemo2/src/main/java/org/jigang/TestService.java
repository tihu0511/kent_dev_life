package org.jigang;

import com.alibaba.dubbo.rpc.RpcException;
import org.jigang.dto.TestInDto;
import org.jigang.ws.ITest;
import org.jigang.ws.IValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by BF100271 on 2016/6/15.
 */
@Service
public class TestService {
    @Autowired
    private ITest test;

    @Autowired
    private IValidationService validationService;

    public void sayHello() {
        test.sayHello();
    }

    public void validationParam() {
        TestInDto inDto = new TestInDto();
        inDto.setId(1);
        inDto.setName("我kent");
        try {
            validationService.validateParam(inDto);
        } catch (RpcException e) {
            ConstraintViolationException ve = (ConstraintViolationException) e.getCause(); // 里面嵌了一个ConstraintViolationException
            Set<ConstraintViolation<?>> violations = ve.getConstraintViolations(); // 可以拿到一个验证错误详细信息的集合
            System.out.println(violations);
            for (ConstraintViolation violation : violations) {
                System.out.println(violation);
                System.out.println(violation.getMessage());
            }
        }
    }

}
