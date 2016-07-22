package org.jigang;

import org.jigang.generator.MybatisGenerator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

/**
 * Created by wujigang on 16/7/12.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring.xml")
public class MybatisGeneratorTest {
    @org.junit.Test
    public void test() throws IOException {
        new MybatisGenerator().generator();
    }

    @Test
    public void testGeneratorResult() {
//        Account account = accountDao.queryById(2);
//        System.out.println(account);
//
//        account.setAccountName("kent_wu");
//        account.setUpdateTime(new Date());
//        accountDao.update(account);
//
//        Account a = new Account();
//        a.setMerchantId(4);
//        a.setAccountName("huozhanbai");
//        a.setAccountPwd("123");
//        a.setState(1);
//        a.setCreateTime(new Date());
//        accountDao.add(a);
    }
}
