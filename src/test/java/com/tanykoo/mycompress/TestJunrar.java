package com.tanykoo.mycompress;


import org.apache.commons.compress.PasswordRequiredException;
import org.apache.commons.compress.archivers.sevenz.SevenZArchiveEntry;
import org.apache.commons.compress.archivers.sevenz.SevenZFile;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @Author ThinkPad
 * Created : 2018-12-25 15:58
 * @Since
 */
public class TestJunrar {
    private static Log log = LogFactory.getLog(TestJunrar.class);

    public static void main(String[] args) {
        FileOutputStream outputStream = null;
        try {
            log.info(Integer.MAX_VALUE);
            SevenZFile sevenZFile = new SevenZFile(new File("E:\\电影\\电影.7z"));
            byte content[] = new byte[1024];
            int count = 0;
            int i = 0;

            SevenZArchiveEntry archiveEntry = sevenZFile.getNextEntry();
            while (archiveEntry != null){

                outputStream = new FileOutputStream(new File("D:\\新建文件夹\\" + archiveEntry.getName()), true);
                long size = archiveEntry.getSize();
                while(size > 0){
                    if(size > 1024){
                        content = new byte[1024];
                        size -= 1024;
                    }else{
                        content = new byte[(int) size];
                        size = 0;
                    }
                    sevenZFile.read(content, 0, content.length);
                    outputStream.write(content);
                }
                outputStream.close();

                archiveEntry = sevenZFile.getNextEntry();
            }
        } catch (PasswordRequiredException e) {
            log.error("file with password");
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        } finally {
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
