package com.tanykoo.compress.rar;

import com.github.junrar.Archive;
import com.github.junrar.LocalFolderExtractor;
import com.github.junrar.UnrarCallback;
import com.github.junrar.Volume;
import com.github.junrar.exception.RarException;
import com.github.junrar.impl.FileVolumeManager;
import com.github.junrar.rarfile.FileHeader;
import com.tanykoo.compress.ArchiveEntry;
import com.tanykoo.compress.ArchiveFile;
import com.tanykoo.compress.Extractor;
import com.tanykoo.compress.UnpackCallBack;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author ThinkPad
 * Created : 2018-12-28 18:01
 * @Since
 */
public class RarExtractor implements Extractor<RarArchiveEntity> {
    private static Log logger = LogFactory.getLog(RarExtractor.class);

    private List<UnpackCallBack> unpackCallBackList = new ArrayList<>();

    private Callback callback = new Callback();

    private long total = 0;


    @Override
    public void extract(ArchiveFile archiveFile, RarArchiveEntity[] archiveEntries, String destDirectory) {
        total = 0;
        for(ArchiveEntry archiveEntry: archiveEntries){
            total += archiveEntry.getSize();
        }
        try {
            Archive archive = new Archive(new FileVolumeManager(archiveFile.getFile()),callback);
            for(FileHeader fileHeader : archive.getFileHeaders()){
                for(int i = 0; i < archiveEntries.length; i++){
                    if(archiveEntries[i].contains(new RarArchiveEntity())){
                        if(createFile(archiveEntries[i],destDirectory))
                            extract(archive ,fileHeader,destDirectory);
                    }
                }
            }
        } catch (RarException e) {
            logger.error("解析文件错误",e);
            e.printStackTrace();
        } catch (IOException e) {
            logger.error("文件IO错误",e);
            e.printStackTrace();
        }

    }

    private boolean createFile(RarArchiveEntity archiveEntry, String destDirectory) {
        File f = new File(destDirectory);

        if(!f.exists()){
            if(callback.isMkdirs(archiveEntry)){
                f.mkdirs();
            }
        }

        return true;

    }

    private void extract(Archive archive, FileHeader archiveEntry, String destDirectory){

    }

    @Override
    public void extract(ArchiveFile archiveFile, String destDirectory) {


    }



    @Override
    public void addUnpackCallBack(UnpackCallBack unpackCallBack) {
        unpackCallBackList.add(unpackCallBack);
    }

    class Callback implements UnrarCallback{

        @Override
        public boolean isNextVolumeReady(Volume nextVolume) {
            return false;
        }

        @Override
        public void volumeProgressChanged(long current, long total) {
            unpackProgressChanged(current,RarExtractor.this.total);
        }

        public void unpackProgressChanged(long current, long total){
            for(UnpackCallBack unpackCallBack : unpackCallBackList){
                unpackCallBack.unpackProgressChanged(current,total);
            }
        }

        public boolean isCover(ArchiveEntry archiveEntry) {
            for(UnpackCallBack unpackCallBack : unpackCallBackList){
                if(!unpackCallBack.isCover(archiveEntry))
                    return false;
            }
            return true;
        }

        public boolean isMkdirs(ArchiveEntry archiveEntry){
            for(UnpackCallBack unpackCallBack : unpackCallBackList){
                if(!unpackCallBack.isMkdirs(archiveEntry))
                    return false;
            }
            return true;
        }

    }



}
