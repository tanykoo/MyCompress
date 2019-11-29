package com.tanykoo.compress.xz;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * @Author ThinkPad
 * Created : 2019-01-01 16:11
 * @Since
 */
public class TestXzUnpack {
    private static Log logger = LogFactory.getLog(TestXzUnpack.class);

    @Test
    public void test(){
        try {
//            XZCompressorInputStream xis = new XZCompressorInputStream(new FileInputStream(new File("C:\\Users\\ThinkPad\\Desktop\\Desktop.tar.xz")));
            BZip2Util.deCompress(new File("C:\\Users\\ThinkPad\\Desktop\\20191118LC\\home.tar.bz2"),false );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
