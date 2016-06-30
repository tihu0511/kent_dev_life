package org.jigang.ws.impl;

import org.jigang.ws.ITest;
import org.springframework.stereotype.Service;

/**
 * Created by BF100271 on 2016/6/15.
 */
@Service
public class TestImpl implements ITest {
    public void sayHello() {
        System.out.println("Hello Dubbo!");
    }
}
