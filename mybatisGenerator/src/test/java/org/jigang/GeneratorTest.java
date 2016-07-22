package org.jigang;

import org.jigang.generator.MybatisGenerator;

import java.io.IOException;

/**
 * Created by wujigang on 16/7/15.
 */
public class GeneratorTest {
    public static void main(String[] args) throws IOException {
        new MybatisGenerator().generator();
    }
}
