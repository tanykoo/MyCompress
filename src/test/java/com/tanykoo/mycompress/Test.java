package com.tanykoo.mycompress;

import com.github.junrar.Archive;
import com.github.junrar.LocalFolderExtractor;
import com.github.junrar.UnrarCallback;
import com.github.junrar.Volume;
import com.github.junrar.exception.RarException;
import com.github.junrar.impl.FileVolumeManager;
import com.github.junrar.rarfile.FileHeader;

import java.io.File;
import java.io.IOException;

/**
 * @author ThinkPad
 * Created : 2019-11-26 17:21
 * @since
 */
public class Test {
    public static void main(String[] args) {
        try {
            Archive archive = new Archive(new FileVolumeManager(new File("D:\\工作\\甘肃业务\\甘肃农信集中对账差错处理平台\\SVN_CCPT_DOC\\差错文档\\03-项目设计\\cccl.rar")), new UnrarCallback() {
                @Override
                public boolean isNextVolumeReady(Volume nextVolume) {

                    System.out.println("1");
                    return false;
                }

                @Override
                public void volumeProgressChanged(long current, long total) {
                    System.out.println(current+" " + total);
                }
            });
            LocalFolderExtractor lfe = new LocalFolderExtractor(new File("D:\\新建文件夹 (2)"));
            try{
                for(final FileHeader fh : archive ) {
                    try {
                        if (fh.isDirectory()) {
                            lfe.createDirectory(fh);
                        } else {
                            lfe.extract(archive, fh);
                        }

                    } catch (final IOException e) {
                        throw e;
                    } catch (final RarException e) {
                        throw e;
                    }
                        System.out.println(getName(fh));
                }
            }finally {
                archive.close();
            }
        } catch (RarException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static String getName(FileHeader fileHeader){
        if(fileHeader.isUnicode()){
            return fileHeader.getFileNameW();
        }else{
            return fileHeader.getFileNameString();
        }
    }
}
