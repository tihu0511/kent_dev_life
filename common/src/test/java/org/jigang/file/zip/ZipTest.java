package org.jigang.file.zip;

import org.junit.Test;

/**
 * Created by wujigang on 16/6/30.
 */
public class ZipTest {
    @Test
    public void zip() {
        String srcPath = "/Users/wendy/develop/tmp/testZip";
        String destFile = "/Users/wendy/develop/tmp/testZip.zip";
        ZipUtil.zip(srcPath, destFile);
    }
}
