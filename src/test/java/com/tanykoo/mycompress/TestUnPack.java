package com.tanykoo.mycompress;

import org.apache.commons.compress.archivers.ArchiveException;
import org.apache.commons.compress.archivers.ArchiveOutputStream;
import org.apache.commons.compress.archivers.ArchiveStreamFactory;
import org.apache.commons.compress.utils.IOUtils;

import java.io.*;

/**
 * @author ThinkPad
 * Created : 2019-11-27 15:27
 * @since
 */
public class TestUnPack {
    public static void main(String[] args) {
//        try {
//            ArchiveInputStream inputStream = new ArchiveStreamFactory().createArchiveInputStream(ArchiveStreamFactory.ZIP,new FileInputStream(new File("C:\\Users\\ThinkPad\\Desktop\\App_ATM_3.9.5.zip")));
//            ArchiveEntry a = null;
//            IOUtils.skip(inputStream,0);
//            System.out.println(System.currentTimeMillis());
//            while ((a = inputStream.getNextEntry()) != null) {
//                File file = new File("D:\\新建文件夹\\新建文件夹\\" + a.getName());
//                FileOutputStream fileOutputStream = null;
//                if(a.isDirectory()){
//                    if(!file.exists()){
//                        file.mkdirs();
//                    }
//                }
//                if(!a.isDirectory()){
//                    if(!file.getParentFile().exists()){
//                        file.getParentFile().mkdirs();
//                    }
//                    fileOutputStream = new FileOutputStream(file);
//                    IOUtils.copy(inputStream,fileOutputStream);
//                }
//                if(fileOutputStream!=null){
//                    fileOutputStream.close();
//                }
//            }
//            System.out.println(System.currentTimeMillis());
//        } catch (ArchiveException e) {
//            e.printStackTrace();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        try {
            ArchiveOutputStream outputStream = new ArchiveStreamFactory().createArchiveOutputStream(ArchiveStreamFactory.ZIP,new FileOutputStream(new File("D:/A.tar")));
            outputStream.putArchiveEntry(outputStream.createArchiveEntry(new File("C:\\Users\\ThinkPad\\Desktop\\data.sql"),"7z/data.sql"));

            InputStream inputStream = new FileInputStream(new File("C:\\Users\\ThinkPad\\Desktop\\data.sql"));
            IOUtils.copy(inputStream,outputStream);
            outputStream.closeArchiveEntry();
            outputStream.close();


        } catch (ArchiveException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
