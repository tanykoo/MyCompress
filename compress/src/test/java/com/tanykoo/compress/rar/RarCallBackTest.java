package com.tanykoo.compress.rar;


import com.github.junrar.Archive;
import com.github.junrar.UnrarCallback;
import com.github.junrar.Volume;
import com.github.junrar.impl.FileVolumeManager;
import com.github.junrar.rarfile.FileHeader;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;

/**
 * @author Tany
 * @createtime 2018-12-30 12:25 AM
 * @since
 */
public class RarCallBackTest {

    private static Log logger = LogFactory.getLog(RarCallBackTest.class);

    @Test
    public void testUnpackRar() throws Exception{
        Archive archive = new Archive(new FileVolumeManager(new File("/Users/Tany/Downloads/offline%2F163ui-offline-2018.05.03-01.rar")),new UnrarCallback(){

            @Override
            public boolean isNextVolumeReady(Volume nextVolume) {
                return false;
            }

            @Override
            public void volumeProgressChanged(long current, long total) {
                logger.info("current :" + current + "total :" + total);
            }
        });

        int i = (int)(Math.random() * archive.getFileHeaders().size());
        for(FileHeader fileHeader : archive.getFileHeaders()){
//            logger.info(new String(fileHeader.getFileNameByteArray()));
//            logger.info(fileHeader.getFileNameString());
        }
        FileOutputStream outputStream = new FileOutputStream("/Users/Tany/wanx/" + archive.getFileHeaders().get(i).getFileNameString());
        archive.extractFile(archive.getFileHeaders().get(i),outputStream);
        outputStream.close();
        outputStream = new FileOutputStream("/Users/Tany/wanx/" + archive.getFileHeaders().get(i-1).getFileNameString());
        archive.extractFile(archive.getFileHeaders().get(i-1),outputStream);
        outputStream.close();


    }
}
