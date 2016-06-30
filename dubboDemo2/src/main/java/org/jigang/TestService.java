package org.jigang;

import org.jigang.ws.ITest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by BF100271 on 2016/6/15.
 */
@Service
public class TestService {
    @Autowired
    private ITest test;

    public void sayHello() {
        test.sayHello();
    }
}
