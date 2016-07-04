package org.jigang;

import com.alibaba.dubbo.config.annotation.Reference;
import org.jigang.ws.ITest;
import org.jigang.ws.IValidationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by BF100271 on 2016/6/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:spring.xml")
public class Test1 {
    @Autowired
    private TestService testService;

    @Test
    public void test() {
        testService.sayHello();
    }

    @Test
    public void testValidate() {
        testService.validationParam();
    }
}
